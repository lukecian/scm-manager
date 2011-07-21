/**
 * Copyright (c) 2010, Sebastian Sdorra
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 * 3. Neither the name of SCM-Manager; nor the names of its
 *    contributors may be used to endorse or promote products derived from this
 *    software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL THE REGENTS OR CONTRIBUTORS BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
 * ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 * http://bitbucket.org/sdorra/scm-manager
 *
 */



package sonia.scm.api.rest.resources;

//~--- non-JDK imports --------------------------------------------------------

import com.google.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sonia.scm.repository.HgRepositoryHandler;
import sonia.scm.repository.HgRepositoryHookEvent;
import sonia.scm.repository.RepositoryManager;
import sonia.scm.repository.RepositoryNotFoundException;

//~--- JDK imports ------------------------------------------------------------

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

/**
 *
 * @author Sebastian Sdorra
 */
@Path("hook/hg/{repository}/{type}")
public class HgHookCallback
{

  /** the logger for HgHookCallback */
  private static final Logger logger =
    LoggerFactory.getLogger(HgHookCallback.class);

  //~--- constructors ---------------------------------------------------------

  /**
   * Constructs ...
   *
   *
   * @param repositoryManager
   * @param handler
   */
  @Inject
  public HgHookCallback(RepositoryManager repositoryManager,
                        HgRepositoryHandler handler)
  {
    this.repositoryManager = repositoryManager;
    this.handler = handler;
  }

  //~--- methods --------------------------------------------------------------

  /**
   * TODO: protect
   *
   *
   *
   * @param repositoryName
   * @param type
   * @param node
   *
   * @return
   */
  @GET
  public Response hookCallback(@PathParam("repository") String repositoryName,
                               @PathParam("type") String type,
                               @QueryParam("node") String node)
  {
    Response response = null;

    try
    {
      repositoryManager.fireHookEvent(HgRepositoryHandler.TYPE_NAME,
                                      repositoryName,
                                      new HgRepositoryHookEvent(handler,
                                        repositoryName, node));
      response = Response.ok().build();
    }
    catch (RepositoryNotFoundException ex)
    {
      if (logger.isErrorEnabled())
      {
        logger.error("could not find repository {}", repositoryName);

        if (logger.isTraceEnabled())
        {
          logger.trace("repository not found", ex);
        }
      }

      response = Response.status(Response.Status.NOT_FOUND).build();
    }

    return response;
  }

  //~--- fields ---------------------------------------------------------------

  /** Field description */
  private HgRepositoryHandler handler;

  /** Field description */
  private RepositoryManager repositoryManager;
}