function limite() {
    if (document.getElementsByName('textoprincipal').values().length > 5) {
        alert("Sabemos que você tem muito a dizer, mas tente evitar textos com mais de 300 caracteres!");
        document.getElementById('a').value = "";
    }
}

function limpo(texto) {
    alert("ok");
    document.getElementById('textoprincipal').innerHTML = "";
    document.getElementById('texto_escolhido').innerHTML=texto;
}


// por favor não mexer nestes codigos abaixo! Obrigado (ainda esta em teste pode ser que seja implementado), esta curioso para saber o que esta belezinha faz ? ele anima o placeholder das paradatudo.

Placeholdem(document.querySelectorAll('[placeholder]'));

function Placeholdem(e) {
    "use strict";
    !function () {
        for (var e = 0, t = ["ms", "moz", "webkit", "o"], n = 0; n < t.length && !window.requestAnimationFrame; ++n)
            window.requestAnimationFrame = window[t[n] + "RequestAnimationFrame"], window.cancelAnimationFrame = window[t[n] + "CancelAnimationFrame"] || window[t[n] + "CancelRequestAnimationFrame"];
        window.requestAnimationFrame || (window.requestAnimationFrame = function (t) {
                var n = (new Date).getTime(), a = Math.max(0, 16 - (n - e)), l = window.setTimeout(function () {
                    t(n + a)
                }, a);
                return e = n + a, l
            }
        ), window.cancelAnimationFrame || (window.cancelAnimationFrame = function (e) {
            clearTimeout(e)
        })
    }();
    var t = {};
    return t.customElems = ["password"], t.defaultInputAttributeName = "data-defaultinputtype", t.init = function () {
        if (t.elems = [], e && e.length) for (var n = 0; n < e.length; n++) t.hasPlaceholder(e[n]) && t.elems.push(new t.PlaceholdemElem(e[n])); else e && t.hasPlaceholder(e) && t.elems.push(new t.PlaceholdemElem(e))
    }, t.hasPlaceholder = function (e) {
        return "function" == typeof e.hasAttribute && e.hasAttribute("placeholder")
    }, t.PlaceholdemElem = function (e) {
        var n = this;
        n.init = function () {
            n.elem = e, n.form = e.form, n.placeholder = n.elem.getAttribute("placeholder"), n.elem.removeAttribute("placeholder"), n.rAF = null, n.animating = 0, n.defaultInputType = n.elem.getAttribute("type"), n.resetDefaultType(), n.elem.value || (n.elem.value = n.placeholder), n.on(n.elem, "focus", n.onFocus), n.on(n.elem, "blur", n.onBlur), n.on(n.elem, "keydown", n.onKeydown), n.form && n.on(n.form, "reset", n.onReset)
        }, n.on = function (e, t, n) {
            e.addEventListener ? e.addEventListener(t, n) : e.attachEvent("on" + t, n)
        }, n.onFocus = function () {
            (n.animating || n.elem.value === n.placeholder) && (n.animating = 1, window.cancelAnimationFrame(n.rAF), n.deletePlaceholder(), n.restoreDefaultType())
        }, n.onBlur = function () {
            (n.animating || "" === n.elem.value) && (n.animating = 1, window.cancelAnimationFrame(n.rAF), n.restorePlaceholder(), n.resetDefaultType())
        }, n.onKeydown = function () {
            n.animating && (n.animating = 0, window.cancelAnimationFrame(n.rAF), n.elem.value = "")
        }, n.onReset = function () {
            setTimeout(function () {
                n.onBlur()
            })
        }, n.deletePlaceholder = function () {
            n.elem.value.length > 0 ? (n.elem.value = n.elem.value.slice(0, -1), n.rAF = window.requestAnimationFrame(n.deletePlaceholder)) : n.animating = 0
        }, n.restorePlaceholder = function () {
            n.elem.value.length < n.placeholder.length ? (n.elem.value += n.placeholder[n.elem.value.length], n.rAF = window.requestAnimationFrame(n.restorePlaceholder)) : n.animating = 0
        }, n.restoreDefaultType = function () {
            var e = n.elem.getAttribute(t.defaultInputAttributeName);
            e && -1 != t.customElems.indexOf(e) && e != n.elem.getAttribute("type") && n.elem.setAttribute("type", e)
        }, n.resetDefaultType = function () {
            -1 != t.customElems.indexOf(n.defaultInputType) && (n.elem.setAttribute(t.defaultInputAttributeName, n.defaultInputType), n.elem.setAttribute("type", "text"))
        }, n.init()
    }, t.init(), t
}

// Aqui termina o codigo de Placeholdem

