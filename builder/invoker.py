
# Motivação inicial do Builder: evitar a explosão de construtores

class Invoker(object):
  # Invoker original: 
  # https://github.com/choreos/enactment_engine/blob/master/Commons/src/main/java/org/ow2/choreos/invoker/DefaultInvoker.java  

  def __init__(self, taskName, task, timeout, trials, pause):
    """
      Argumentos:
        taskName - nome da tarefa -- string
        task - tarefa a ser executada -- uma função
        timeout - tempo limite para uma tentatvia em segundos -- int
        trials - quantidade de tentativas em segundos -- int
        pause - pausa entre as tentativas em segundos -- int
    """
    self.taskName = taskName
    self.task = task
    self.timeout = timeout
    self.trials = trials
    self.pause = pause

  def invoke(self):
    # faz de conta que tá usando timeout, trials, e pause
    try:
      return self.task()
    except:
      raise RuntimeError('%s falhou' % taskName)






class InvokerBuilder(object):
  # InvokerBuilder original:
  # https://github.com/choreos/enactment_engine/blob/master/Commons/src/main/java/org/ow2/choreos/invoker/InvokerBuilder.java

  DEFAULT_TRIALS = 1
  DEFAULT_PAUSE = 0

  def __init__(self, taskName, task, timeout):
    self.taskName = taskName
    self.task = task
    self.timeout = timeout
    self.trials = DEFAULT_TRIALS
    self.pause = DEFAULT_PAUSE

  def trials(trials):
    self.trials = trials
    return self

  def pause(pause):
    self.pause = pause
    return self

  def build(self):
    return Invoker(self.taskName, self.task, self.timeout, self.trials, self.pause)



# usando o builder, ficaria assim
f = lambda x : x*x
invoker = InvokerBuilder("quadrado", f, 100).trials(3).pause(1).build()

# funciona, mas... me parece q não ficou mt pythonico, neh?

######################################


# Uma primeira tentativa um **pouco** mais pythonica 
class Invoker(object):
  # Invoker original: 
  # https://github.com/choreos/enactment_engine/blob/master/Commons/src/main/java/org/ow2/choreos/invoker/DefaultInvoker.java  

  def __init__(self, taskName, task, timeout, trials=1, pause=0):
    """
      Argumentos:
        taskName - nome da tarefa -- string
        task - tarefa a ser executada -- uma função
        timeout - tempo limite para uma tentatvia em segundos -- int
        trials - quantidade de tentativas em segundos -- int (padrão é 1)
        pause - pausa entre as tentativas em segundos -- int (padrão é 0)
    """
    self.taskName = taskName
    self.task = task
    self.timeout = timeout
    self.trials = trials
    self.pause = pause

  def invoke(self):
    # faz de conta que tá usando timeout, trials, e pause
    try:
      return self.task()
    except:
      raise RuntimeError('%s falhou' % taskName)

# Problema: E se eu quiser especificar pause, mas não trials?

# Solução 1: jogar no init só oq é obrigatório, e settar o resto linha a linha

f = lambda x : x*x
invoker = Invoker("quadrado", f, 100)
invoker.trials = 3
invoker.pause = 1 
# mt feio, neh?


# Solução 2: kwargs!
# TODO implementar
# mostrar exemplo de kargs da query do django
# draw-back: documentação fica mais complicada
# e como fica a introspecção?

# Até agora consideramos o Builder como uma forma de "instanciar o objeto de uma vez"
# mas ele tb possui uma outra motivação mais profunda, ele ajuda a criar objetos imutáveis.
# Objetos imutáveis tem suas vantagens [ref effective Java]
# Vide programação funcional: totalmente baseada na imutabilidade dos objetos
# Mas...... considerando o não-encapsulamento do python, faz sentido usar o Builder para esse propósito?
# faz sentido se preocupar com imutabilidade de objetos no Python?












