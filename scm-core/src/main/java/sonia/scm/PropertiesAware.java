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



package sonia.scm;

//~--- JDK imports ------------------------------------------------------------

import java.util.Map;

/**
 * Base interface of all objects which have properties.
 *
 * @since 1.6
 * @author Sebastian Sdorra
 */
public interface PropertiesAware
{

  /**
   * Removes a existing property.
   *
   * @param key - the key of the property
   */
  public void removeProperty(String key);

  //~--- get methods ----------------------------------------------------------

  /**
   * Returns all properties.
   *
   * @return all properties
   */
  public Map<String, String> getProperties();

  /**
   * Returns the property value for the given key
   * or null if the key does not exists.
   *
   * @param key - the key of the property
   * @return the value of the property
   */
  public String getProperty(String key);

  //~--- set methods ----------------------------------------------------------

  /**
   * Sets all properties and overwrites existing ones.
   *
   * @param properties to set
   */
  public void setProperties(Map<String, String> properties);

  /**
   * Sets a single property.
   *
   * @param key - The key of the property
   * @param value - The value of the property
   */
  public void setProperty(String key, String value);
}