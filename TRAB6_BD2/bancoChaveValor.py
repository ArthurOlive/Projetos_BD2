import redis

class chave_valor():
    def __init__(self):
        print("Class criada")
        self.r = redis.Redis(host='localhost', port=6379, db=0)

    def inserir(self, key, value):
        print(key , " : ", value)
        self.r.rpush(key, value)

    def remover_value(self, key, value):
        lista = self.r.lrange(key, 0, -1)
        lista_n = []
        self.r.delete(key)

        for i in lista:
            lista_n.append(str(i))

        lista_n = [ l.replace("b'", '').replace("'", '') for l in lista_n ]

        for i in lista_n:
            if i != value:
                self.r.rpush(key, i)

    def listar(self):
        dicionario = {}
        lista_j = []
        lista_n = []

        for i in self.r.keys():
            lista_n.append(str(i))

        lista_n = [ l.replace("b'", '').replace("'", '') for l in lista_n ]

        for i in range(len(lista_n)):
            lista_x = []
            for i in self.r.lrange(self.r.keys()[i], 0, -1):
                lista_x.append(str(i))
            lista_j.append(lista_x)


        for i in range(len(lista_j)):
            dicionario[lista_n[i]] = [ l.replace("b'", '').replace("'", '') for l in lista_j[i] ]

        return(dicionario)

    def remover(self, key):
        self.r.delete(key)

#r = redis.Redis(host='localhost', port=6379, db=0)
#r.set('foo', 'bar')
#r.delete('foo')
#r.rpush('foo', 'bor')#incrementa a mais na chave
#r.rpush('foo', 'bar')
#r.rpush('foo', 'big')
#print(r.lrange('foo', 0, -1))