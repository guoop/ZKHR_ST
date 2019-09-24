// var com_devEnv = 'dev';
var com_devEnv = 'prod';

function formatDate(dateStr) {
  // dateStr = '2011-10-10T14:48:00'; // origin
  // "2019-06-03 14:31:09" // new
  function fz(str) {
    return String(str).length < 2 ? '0' + str : str;
  }
  var da = new Date(dateStr);
  var y = da.getFullYear(),
    m = fz(da.getMonth() + 1),
    d = fz(da.getDate()),
    h = fz(da.getHours()),
    i = fz(da.getMinutes()),
    s = fz(da.getSeconds());
  return y + '-' + m + '-' + d + ' ' + h + ':' + i + ':' + s;
}


(function() {
  window.onload = function() {
    // testLinks();
  };

  function testLinks() {
    var baseUrl = '../../static';
    var tags = [{
        tagName: 'link',
        attrName: 'href',
        baseUrl: baseUrl,
      },
      {
        tagName: 'script',
        attrName: 'src',
        baseUrl: baseUrl,
      },
      {
        tagName: 'img',
        attrName: 'src',
        baseUrl: baseUrl,
      },
      {
        tagName: 'a',
        attrName: 'href',
        baseUrl: '../../templates',
      },
      {
        tagName: 'a',
        attrName: 'data-href',
        baseUrl: '../../templates',
      },
    ];

    tags.forEach(function(ele) {
      formatUrl(ele.tagName, ele.attrName, ele.baseUrl);
    });

    function formatUrl(tagName, attr, baseUrl) {
      var doms = Array.prototype.slice.call(document.querySelectorAll(tagName));
      doms.forEach(function(ele, index) {
        var attrstr = ele.getAttribute(attr);
        if (attrstr === null && tagName == 'script') {
          var eleJsStr = ele.innerHTML;
          ele.remove();
          setTimeout(function() {
            eval(eleJsStr);
            // var funJsStr = new Function(eleJsStr);
            // funJsStr();
            console.warn('静态资源加载完成');
          }, (doms.length + 1) * 100);
        }
        if (attrstr && attrstr.indexOf('/') === 0) {
          // console.log(attrstr);
          if (tagName == 'script') {
            setTimeout(function() {
              var script = document.createElement("script");
              script.type = "text/javascript";
              // script.async = true;
              script.defer = true;
              script.src = baseUrl + attrstr;
              document.head.appendChild(script);
            }, 100 * index);
          } else if (tagName == 'a') {
            ele.setAttribute(attr, baseUrl + attrstr + '.html');
            // ele[attr] = baseUrl + attrstr + '.html';
          } else {
            // ele[attr] = baseUrl + attrstr;
            ele.setAttribute(attr, baseUrl + attrstr);
          }
        }
      });
    }
  }
})();