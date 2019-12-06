from flask import Flask, request, current_app, send_from_directory, render_template, redirect, url_for
from bancoChaveValor import chave_valor as cv
import formulario, deletar

app = Flask(__name__)

@app.route("/", methods=['GET', 'POST'])
def home_page():
    chave_valor = cv()
    dicionario = chave_valor.listar()
    form = formulario.Formulario(request.form)
    form_del = deletar.Formulario(request.form)
    title = "App TRAB6"
    if request.method == 'POST' and (form.key.data != "" and form.value.data != ""):
        chave_valor.inserir(form.key.data, form.value.data)
        form.key.data = ""
        form.value.data = ""
        form_del.key_del.data = ""
        dicionario = chave_valor.listar()
        request.data = ""
        return redirect(request.path,code=302)
    elif request.method == "POST" and form_del.key_del.data != "":
        if form_del.value_del.data != "":
            chave_valor.remover_value(form_del.key_del.data, form_del.value_del.data)
            form.key.data = ""
            form.value.data = ""
            form_del.key_del.data = ""
            form_del.value_del.data = ""
            dicionario = chave_valor.listar()
            request.data = ""
        else:
            chave_valor.remover(form_del.key_del.data)
            form.key.data = ""
            form.value.data = ""
            form_del.key_del.data = ""
            form_del.value_del.data = ""
            dicionario = chave_valor.listar()
            request.data = ""
    return render_template("pagina.html", title = title, form = form, len = len(dicionario), 
    keys_lista =list(dicionario.keys()), values_lista = list(dicionario.values()), form_del = form_del)


if __name__ == "__main__": 
    app.run(debug=True, port=8000)