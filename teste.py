from threading import Thread

contador = 0 #recurso compartilhado
liberado = True
fila_espera = []


def acquire(nome):

    global liberado

    if liberado:
        liberado = False
        print("O mutex está livre")
        print(nome, "entrou na seção crítica")

    else:
        print(nome, "entrou na fila de espera")
        print("O mutex está ocupado.")
        fila_espera.append(nome)


def release(nome):

    global liberado

    print(nome, "liberando a sessão")
    
    liberado = True

    if len(fila_espera) > 0:
        proximo = fila_espera.pop(0)
        acquire(proximo)

    print(f"{nome} saiu. Mutex liberado.")

def incrementar(nome):
    global contador

    #ACQUIRE
    acquire(nome)


    #seção crítica
    for _ in range(10):
        print(nome, " dentro da sessão crítica, incrementando ", contador)

        contador += 1


    #RELEASE
    print("Liberando sessão")

    release(nome)





t1 = Thread(target=incrementar, args=("t1",))
t2 = Thread(target=incrementar, args=("t2",))

t1.start()
t2.start()

t1.join()
t2.join()
