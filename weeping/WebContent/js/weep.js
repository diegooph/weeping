//Verificação para campo texto / contagem de caracteres e frases absurdas.


$(document).on("input", "#textoprincipal", function () {
    var contador = 0;
    var cd = $(this).val().length;
    var cr = contador + cd;

    $("#caracteres").text(cr);

    if (cr < 15) {
        $("#referencia").text("...")
    }
    if (cr >= 15) {
        $("#referencia").text("Você me parece uma pessoa de poucas palavras")
    }
    if (cr >= 30) {
        $("#referencia").text("Talvez eu estivesse errado... Esta indo bem, continue a digitar!")
    }
    if (cr >= 60) {
        $("#referencia").text("Sinto a sua dor ao digitar, não desista agora!")
    }
    if (cr >= 150) {
        $("#referencia").text("Muahahahaha")
    }
    if (cr >= 200) {
        $("#referencia").text("WOW, vai com calma! voce pode machucar alguem são muitas palavras ai!")
    }
    if (cr >= 300) {
        $("#referencia").text(" Você chegou ao limite... isso não esta me cheirando bem.")
    }

});


function limite() {

    var campo = document.getElementById('textoprincipal');
    var txt = document.getElementById('textoprincipal').value.trim();
    txt.replace(" ", "");
    var n = txt.length;


    alert("O Texto digitado tem " + n + " caracteres");
    campo.focus();

    if (n == "" || n == null) {
        alert("Você não pode postar nada se não tiver um texto...");
        campo.focus;
    } else if (n > 300) {
        alert("Sabemos que você tem muito a dizer, mas voce chegou ao limite de 300 caracteres!");
        campo.focus;
    } else if (n <= 4) {
        alert("O minimo de caracteres é 4, seja mais criatuvo.");
        campo.focus;
    } else {
        alert("Tudo Ok");
    }

}

function limpar() {

    if (document.getElementById('textoprincipal').value == "" || document.getElementById('textoprincipal').value == null) {
        document.getElementById('textoprincipal').value = "Pronto, digitamos um texto aqui para você ter o que apagar, va em frente, pode fazer isso a vontade!!.";
    } else {
        document.getElementById('textoprincipal').value = "";
    }
}


//Fim verificação para campo texto.

//validação de campos cadastro 1º
function validarCampos() {

    if (document.getElementById('cep').value == null || document.getElementById('cep').value == "") {
alert("Eayyahhh");

    } 
}

// Make it rain baby (Nao altere os valores)
// use um canvas para funcionar (ainda esta em teste mas se funcionar na pagina principal vai dar bom)  <canvas id="canvas">
/*
$(document).ready(function () {
    var canvas = $('.canvas')[0];
    canvas.width = window.innerWidth;
    canvas.height = window.innerHeight;

    if (canvas.getContext) {
        var ctx = canvas.getContext('2d');
        var w = canvas.width;
        var h = canvas.height;
        ctx.strokeStyle = 'rgba(174,194,224,1.5)';
        ctx.lineWidth = 1;
        ctx.lineCap = 'round';

        var init = [];
        var maxParts = 1000;
        for (var a = 0; a < maxParts; a++) {
            init.push({
                x: Math.random() * w,
                y: Math.random() * h,
                l: Math.random() * 1,
                xs: -4 + Math.random() * 4 + 2,
                ys: Math.random() * 10 + 10
            })
        }

        var particles = [];
        for (var b = 0; b < maxParts; b++) {
            particles[b] = init[b];
        }

        function draw() {
            ctx.clearRect(0, 0, w, h);
            for (var c = 0; c < particles.length; c++) {
                var p = particles[c];
                ctx.beginPath();
                ctx.moveTo(p.x, p.y);
                ctx.lineTo(p.x + p.l * p.xs, p.y + p.l * p.ys);
                ctx.stroke();
            }
            move();
        }

        function move() {
            for (var b = 0; b < particles.length; b++) {
                var p = particles[b];
                p.x += p.xs;
                p.y += p.ys;
                if (p.x > w || p.y > h) {
                    p.x = Math.random() * w;
                    p.y = -20;
                }
            }
        }

        setInterval(draw, 30);

    }
});
*/
