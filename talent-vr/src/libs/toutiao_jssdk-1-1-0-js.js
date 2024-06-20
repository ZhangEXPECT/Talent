export var tt = (function () {
  function e(e) {
    for (var n = "", t = new Uint8Array(e), r = t.byteLength, o = 0; o < r; o++)
      n += String.fromCharCode(t[o]);
    return _(n);
  }
  function n(e) {
    for (var n = C(e), t = n.length, r = new Uint8Array(t), o = 0; o < t; o++)
      r[o] = n.charCodeAt(o);
    return r.buffer;
  }
  function t(n) {
    return { base64: e(n) };
  }
  function r(e) {
    if (null != e) return e.base64 ? n(e.base64) : void 0;
  }
  function o(e) {
    return function (n) {
      return (
        Object.prototype.toString.call(n).toLowerCase() ===
        ("[object " + e + "]").toLowerCase()
      );
    };
  }
  function a(e) {
    if (!1 === o("Object")(e)) return e;
    var n = [];
    for (var r in e) {
      var a = e[r];
      if (void 0 !== a && a instanceof ArrayBuffer && void 0 !== a.byteLength) {
        var i = t(a);
        (i.key = r), n.push(i);
      }
    }
    if (n.length > 0) {
      for (var c = 0; c < n.length; c++) delete e[n[c].key];
      e.__nativeBuffers__ = n;
    }
    return e;
  }
  function i(e) {
    if (!1 === o("Object")(e) || null == e.__nativeBuffers__) return e;
    var n = e.__nativeBuffers__;
    delete e.__nativeBuffers__;
    for (var t = 0; t < n.length; t++) {
      var a = n[t];
      if (null != a) {
        var i = r(a);
        void 0 !== i && i instanceof ArrayBuffer && (e[a.key] = i);
      }
    }
    return e;
  }
  function c(e, n) {
    if (void 0 !== e && "function" == typeof J[n] && "" !== e && null !== e) {
      try {
        (e = JSON.parse(e)), (e = B.unpack(e));
      } catch (n) {
        e = {};
      }
      J[n](e), delete J[n];
    }
  }
  function f(e, n, t) {
    if (
      M &&
      N.needCache.find(function (n) {
        return n === e;
      })
    )
      return void I.push([e, n, t]);
    if (k) c(k.invoke(e, n, t), t);
    else {
      var r = { event: e, paramsString: n, callbackId: t };
      S.messageHandlers.invoke.postMessage(r);
    }
  }
  function s(e, n, t) {
    k
      ? k.publish(e, n, t)
      : S.messageHandlers.publish.postMessage({
          event: e,
          paramsString: n,
          webviewIds: t,
        });
  }
  function u(e, n, t) {
    n = B.pack(n);
    var r = JSON.stringify(n || {}),
      o = ++O;
    (J[o] = t), "openSchema" === e && E.push(o), f(e, r, o);
  }
  function p(e, n) {
    "string" == typeof n && (n = JSON.parse(n)), (n = B.unpack(n));
    var t = J[e];
    "function" == typeof t && t(n), -1 === E.indexOf(e) && delete J[e];
  }
  function l(e, n) {
    F[e] = n;
  }
  function g(e, n, t) {
    (t = t || []),
      (t = JSON.stringify(t)),
      s("custom_event_" + e, JSON.stringify(n), t);
  }
  function d(e, n) {
    L["custom_event_" + e] = n;
  }
  function v(e, n, t, r) {
    if (e === T.onAppEnterBackground) M = !0;
    else if (e === T.onAppEnterForeground)
      for (M = !1; I.length; ) f.apply(null, I.shift());
    if (
      !M ||
      !N.needDisabled.find(function (n) {
        return n === e;
      })
    ) {
      "string" == typeof n && (n = JSON.parse(n)), (n = B.unpack(n));
      var o = e.indexOf("custom_event_") > -1 ? L[e] : F[e];
      "function" == typeof o && o(n, t, r);
    }
  }
  function h(e) {
    e();
  }
  function m() {
    var e = Array.prototype.slice.call(arguments);
    (e[1] = { data: e[1], options: { timestamp: Date.now() } }),
      h(function () {
        D.publish.apply(D, e);
      });
  }
  function b(e) {
    var n = e.name,
      t = void 0 === e.type ? "sdk" : e.type,
      r = void 0 === e.args ? {} : e.args,
      o = void 0 === e.ext ? {} : e.ext;
    (x[j] = {
      success: r.success || R,
      fail: r.fail || R,
      complete: r.complete || R,
    }),
      (H[j] = {
        beforeAll: o.beforeAll || R,
        beforeSuccess: o.beforeSuccess || R,
        afterSuccess: o.afterSuccess || R,
        beforeFail: o.beforeFail || R,
        afterFail: o.afterFail || R,
        afterAll: o.afterAll || R,
      }),
      m("invokeAppServiceMethod", { name: n, type: t, args: r, callbackId: j }),
      (j += 1);
  }
  var y = new Function("return this;")(),
    k = y.ttJSCore,
    S = y.webkit;
  y.ttJSCore &&
    (y.ttJSCore = {
      onDocumentReady: function () {
        return k.onDocumentReady();
      },
    });
  var A = navigator.userAgent.toLocaleLowerCase().includes("toutiaomicroapp");
  y.webkit &&
    A &&
    (y.webkit = {
      messageHandlers: {
        onDocumentReady: {
          postMessage: function () {
            return S.messageHandlers.onDocumentReady.postMessage("");
          },
        },
      },
    });
  var w = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=",
    _ =
      _ ||
      function (e) {
        for (
          var n, t, r = String(e), o = "", a = 0, i = w;
          r.charAt(0 | a) || ((i = "="), a % 1);
          o += i.charAt(63 & (n >> (8 - (a % 1) * 8)))
        ) {
          if ((t = r.charCodeAt((a += 0.75))) > 255)
            throw new Error('"btoa" failed');
          n = (n << 8) | t;
        }
        return o;
      },
    C =
      C ||
      function (e) {
        var n = String(e).replace(/=+$/, ""),
          t = "";
        if (n.length % 4 == 1) throw new Error('"atob" failed');
        for (
          var r, o, a = 0, i = 0;
          (o = n.charAt(i++));
          ~o && ((r = a % 4 ? 64 * r + o : o), a++ % 4)
            ? (t += String.fromCharCode(255 & (r >> ((-2 * a) & 6))))
            : 0
        )
          o = w.indexOf(o);
        return t;
      },
    B = { new: t, get: r, pack: a, unpack: i },
    J = {},
    O = 0,
    F = {},
    L = {},
    I = [],
    M = !1,
    E = [],
    T = {
      onAppEnterBackground: "onAppEnterBackground",
      onAppEnterForeground: "onAppEnterForeground",
    },
    N = {
      needCache: ["showModal", "showToast", "showActionSheet", "hideToast"],
      needDisabled: ["onAccelerometerChange", "onCompassChange"],
    },
    D = { on: l, publish: g, invoke: u, subscribe: d };
  y.ttJSBridge = {
    get invokeHandler() {
      return p;
    },
    get subscribeHandler() {
      return v;
    },
  };
  var j = 0,
    x = [],
    H = [],
    R = function () {};
  !(function () {
    var e = Array.prototype.slice.call(arguments),
      n = e[1];
    (e[1] = function (e, t) {
      "string" == typeof e && (e = JSON.parse(e));
      var r = e.data;
      "function" == typeof n && n(r, t);
    }),
      h(function () {
        D.subscribe.apply(D, e);
      });
  })("callbackAppServiceMethod", function (e) {
    var n = e.res,
      t = e.isSuccess,
      r = e.callbackId,
      o = x[r],
      a = H[r];
    a.beforeAll(n),
      t
        ? (a.beforeSuccess(n), o.success(n), a.afterSuccess(n))
        : (a.beforeFail(n), o.fail(n), a.afterFail(n)),
      o.complete(n),
      a.afterAll(n);
  });
  var P = function (e, n) {
    b({ name: e, args: n, type: "jssdk" });
  };
  return {
    miniProgram: {
      redirectTo: function (e) {
        P("redirectTo", e);
      },
      navigateTo: function (e) {
        P("navigateTo", e);
      },
      switchTab: function (e) {
        P("switchTab", e);
      },
      reLaunch: function (e) {
        P("reLaunch", e);
      },
      navigateBack: function (e) {
        P("navigateBack", e);
      },
      postMessage: function (e) {
        e && P("postMessage", e);
      },
      setSwipeBackModeSync: function () {
        var e =
          arguments.length > 0 && void 0 !== arguments[0] ? arguments[0] : 0;
        P("setSwipeBackMode", { mode: e });
      },
      checkJsApi: function (e) {
        P("checkJsApi", e);
      },
      getEnv: function (e) {
        if (!A) return e({ miniProgram: !1 });
        P("getEnv", {
          useCache: !0,
          complete: function (n) {
            e({ systemInfo: n, miniprogram: A });
          },
        });
      },
      chooseImage: function (e) {
        P("chooseImage", e);
      },
      compressImage: function (e) {
        P("compressImage", e);
      },
      previewImage: function (e) {
        P("previewImage", e);
      },
      uploadFile: function (e) {
        P("uploadFile", e);
      },
      getNetworkType: function (e) {
        P("getNetworkType", e);
      },
      openLocation: function (e) {
        P("openLocation", e);
      },
      getLocation: function (e) {
        P("getLocation", e);
      },
    },
  };
})();
