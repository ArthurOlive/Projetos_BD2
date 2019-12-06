from wtforms import Form, StringField, TextField

class Formulario(Form):
    key = StringField("Chave")
    value = StringField("Valor")

