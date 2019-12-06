from wtforms import Form, StringField, TextField

class Formulario(Form):
    key_del = StringField("Chave")
    value_del = StringField("Valor")