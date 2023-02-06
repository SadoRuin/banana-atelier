const { createProxyMiddleware } = require('http-proxy-middleware');

module.exports = function(app){
  app.use(
    createProxyMiddleware('/api', {
      target: 'https://i8a108.p.ssafy.io:8447',
      pathRewrite: {
        '^/api ':''
      },
      changeOrigin: true
    })
  )
  
  
  
};