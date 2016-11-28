// seajs config
seajs.config({
    base : "/sea-modules/",
    alias : {
        "jquery" : "//cdn.bootcss.com/jquery/1.11.3/jquery.js",
        "bootstrap" : "//cdn.bootcss.com/bootstrap/3.3.6/js/bootstrap.min.js",
        "plupload" : "//cdn.bootcss.com/plupload/2.1.8/plupload.full.min.js",
        "vue" : "//cdn.bootcss.com/vue/2.0.7/vue.js",
        "canvas":"//cdn.bootcss.com/canvas-nest.js/1.0.0/canvas-nest.min.js",
        "router":"//cdn.bootcss.com/vue-router/2.0.3/vue-router.min.js"
    },
    preload : [ 'jquery' ]
})
