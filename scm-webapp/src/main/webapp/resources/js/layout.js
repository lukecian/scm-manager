/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


Ext.onReady(function(){

  // NOTE: This is an example showing simple state management. During development,
  // it is generally best to disable state management as dynamically-generated ids
  // can change across page loads, leading to unpredictable results.  The developer
  // should ensure that stable state ids are set for stateful components in real apps.
  Ext.state.Manager.setProvider(new Ext.state.CookieProvider());

  var tabPanel = new Ext.TabPanel({
      region: 'center', // a center region is ALWAYS required for border layout
      deferredRender: false,
      activeTab: 1,     // first tab initially active
      items: [{
        id: 'welcome',
        xtype: 'panel',
        title: 'Welcome',
        // closable: true,
        autoScroll: true
      },{
        id: 't_sample',
        xtype: 'restGrid',
        title: 'Sample',
        autoScroll: true,
        store: groupStore,
        cm: groupColModel,
        idField: 'name',
        searchField: 'name',
        editForm: 'sampleEditForm',
        restAddUrl: restUrl + 'groups.json',
        restEditUrlPattern: restUrl + 'groups/{0}.json',
        restRemoveUrlPattern: restUrl + 'groups/{0}.json'
      }]
    });

  new Ext.Viewport({
    layout: 'border',
    items: [
    // create instance immediately
    new Ext.BoxComponent({
      region: 'north',
      id: 'north-panel',
      contentEl: 'north',
      height: 75
    }), {
      region: 'west',
      id: 'west-panel', // see Ext.getCmp() below
      title: 'West',
      split: true,
      width: 200,
      minSize: 175,
      maxSize: 400,
      collapsible: true,
      margins: '0 0 0 5',
      layout: {
        type: 'accordion',
        animate: true
      },
      items: [{
        contentEl: 'west',
        title: 'Navigation',
        border: false,
        iconCls: 'nav' // see the HEAD section for style used
      }, {
        title: 'Settings',
        contentEl: 'settings',
        border: false,
        iconCls: 'settings'
      }]
    },
    new Ext.BoxComponent({
      region: 'south',
      id: 'south-panel',
      contentEl: 'south',
      height: 16,
      margins: '2 2 2 5'
    }),
    // in this instance the TabPanel is not wrapped by another panel
    // since no title is needed, this Panel is added directly
    // as a Container
    tabPanel
  ]});

  function addGroupPanel(){
    tabPanel.add({
      id: 't_group',
      xtype: 'groupGrid',
      title: 'Groups',
      closable: true,
      autoScroll: true
    });
    tabPanel.setActiveTab('t_group');
  }

  function createMainMenu(){
    var menu = Ext.get( 'main-menu' );
    var groupsLink = menu.createChild({
      tag: 'li',
      html: 'Groups',
      style: 'cursor: pointer;'
    });
    groupsLink.on('click', addGroupPanel);
  }

  Ext.Ajax.request({
    url: restUrl + 'authentication.json',
    method: 'GET',
    success: function(){
      createMainMenu();
    },
    failure: function(){
      var loginWin = new Sonia.login.Window();
      loginWin.on('success', function(){
        createMainMenu();
      });
      loginWin.show();
    }
  });

});