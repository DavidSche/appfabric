window.camCockpitConf = {
  // // custom libraries and scripts loading and initialization,
  // // see: http://docs.camunda.org/guides/user-guide/#tasklist-customizing-custom-scripts
  // customScripts: {
  //   // AngularJS module names
  //   ngDeps: ['ui.bootstrap'],
  //   // RequireJS configuration for a complete configuration documentation see:
  //   // http://requirejs.org/docs/api.html#config
  //   deps: ['jquery', 'custom-ui'],
  //   paths: {
  //     // if you have a folder called `custom-ui` (in the `cockpit` folder)
  //     // with a file called `scripts.js` in it and defining the `custom-ui` AMD module
  //     'custom-ui': 'custom-ui/scripts'
  //   },
  // historicActivityInstanceMetrics: {
  //   adjustablePeriod: true,
  //   //select from the default time period: today, week, month, complete
  //   period: {
  //     unit: 'week'
  //   }
  // },
  // set if a user can change the default or no
  // userCanChangePeriod: true/false
  // },
  app: {
    name: '${CUSTOM_COCKPIT_TITLE}',
    vendor: ' '
  },
  'locales': {
    'availableLocales': ['${CUSTOM_LANG}', 'en'],
    'fallbackLocale': 'en'
  },
  // skipCustomListeners: {
  //   default: true,
  //   hidden: false
  // },
  // 'batchOperation' : {
  //   // select mode of query for process instances or decision instances
  //   // possible values: filter, search
  //   'mode': 'filter'
  // }
};
