
# Motivação inicial do Builder: evitar a explosão de construtores

class Invoker(object):
  # Invoker original: 
  # https://github.com/choreos/enactment_engine/blob/master/Commons/src/main/java/org/ow2/choreos/invoker/DefaultInvoker.java  

  def __init__(self, taskName, task, timeout, trials, pause, time_unit):
    """
      Argumentos:
        taskName - nome da tarefa -- string
        task - tarefa a ser executada -- uma função
        timeout - tempo limite para uma tentatvia em segundos -- int
        trials - quantidade de tentativas em segundos -- int
        pause - pausa entre as tentativas em segundos -- int
        time_unit - string \in {'sec', 'min'}
    """
    self.taskName = taskName
    self.task = task
    self.timeout = timeout
    self.trials = trials
    self.pause = pause
    self.time_unit = time_unit

  def invoke(self):
    # faz de conta que tá usando timeout, trials, pause e time_unit
    try:
      return self.task()
    except:
      raise RuntimeError('%s falhou' % taskName)






class InvokerBuilder(object):
  # InvokerBuilder original:
  # https://github.com/choreos/enactment_engine/blob/master/Commons/src/main/java/org/ow2/choreos/invoker/InvokerBuilder.java

  DEFAULT_TRIALS = 1
  DEFAULT_PAUSE = 0
  DEFAULT_TIME_UNIT = 'sec'

  def __init__(self, taskName, task, timeout, time_unit):
    self.taskName = taskName
    self.task = task
    self.timeout = timeout
    self.trials = DEFAULT_TRIALS
    self.pause = DEFAULT_PAUSE
    self.time_unit = DEFAULT_TIME_UNIT

  def trials(trials):
    self.trials = trials
    return self

  def pause(pause):
    self.pause = pause
    return self

  def pause(time_unit):
    self.time_unit = time_unit
    return self

  def build(self):
    return Invoker(self.taskName, self.task, self.timeout, self.trials, self.pause)



# usando o builder, ficaria assim
f = lambda x : x*x
invoker = InvokerBuilder("quadrado", f, 1).trials(3).pause(1).time_unit('min').build()

# funciona, mas... me parece q não ficou mt pythonico, neh?

######################################


# Uma primeira tentativa um **pouco** mais pythonica 
class Invoker(object):
  # Invoker original: 
  # https://github.com/choreos/enactment_engine/blob/master/Commons/src/main/java/org/ow2/choreos/invoker/DefaultInvoker.java  

  def __init__(self, taskName, task, timeout, trials=1, pause=0, time_unit='sec'):
    """
      Argumentos:
        taskName - nome da tarefa -- string
        task - tarefa a ser executada -- uma função
        timeout - tempo limite para uma tentatvia em segundos -- int
        trials - quantidade de tentativas em segundos -- int (padrão é 1)
        pause - pausa entre as tentativas em segundos -- int (padrão é 0)
        time_unit - string \in {'sec', 'min'} (padrão é 'sec')
    """
    self.taskName = taskName
    self.task = task
    self.timeout = timeout
    self.trials = trials
    self.pause = pause
    self.time_unit = time_unit

  def invoke(self):
    # faz de conta que tá usando timeout, trials, e pause
    try:
      return self.task()
    except:
      raise RuntimeError('%s falhou' % taskName)

# Problema: E se eu quiser especificar pause, mas não trials?
#(bom, se não fosse o time_unit até q essa versão resolvia o problema, pois não faz sentido especificar pause se trials=1)

# Solução 1: jogar no init só oq é obrigatório, e settar o resto linha a linha

f = lambda x : x*x
invoker = Invoker("quadrado", f, 100)
invoker.trials = 3
invoker.pause = 1 
# mt feio, neh?


# Solução 2: kwargs!
# TODO implementar
# mostrar exemplo de kargs da query do django
# draw-back: perda de expressividade; documentação fica mais complicada
# e como fica a introspecção?

class Invoker(object):

  DEFAULT_TRIALS = 1
  DEFAULT_PAUSE = 0
  DEFAULT_TIME_UNIT = 'sec'

  def __init__(self, taskName, task, timeout, **kwargs):
    """
      Argumentos:
        taskName - nome da tarefa -- string
        task - tarefa a ser executada -- uma função
        timeout - tempo limite para uma tentatvia em segundos -- int
        kwargs:
            trials - quantidade de tentativas em segundos -- int (padrão é 1)
            pause - pausa entre as tentativas em segundos -- int (padrão é 0)
            time_unit - string \in {'sec', 'min'} (padrão é 'sec')
    """
    self.taskName = taskName
    self.task = task
    self.timeout = timeout
    self.trials = kwargs.get('trials', DEFAULT_TRIALS)
    self.pause = kwargs.get('pause', DEFAULT_PAUSE)
    self.time_unit = kwargs.get('time_unit', DEFAULT_TIME_UNIT)

  def invoke(self):
    # faz de conta que tá usando timeout, trials, e pause
    try:
      return self.task()
    except:
      raise RuntimeError('%s falhou' % taskName)


# Usando Invoker criado com kwargs:
invoker = Invoker("quadrado", f, 100, trials=3, pause=2)

# Até agora consideramos o Builder como uma forma de "instanciar o objeto de uma vez"
# mas ele tb possui uma outra motivação mais profunda, ele ajuda a criar objetos imutáveis.
# Objetos imutáveis tem suas vantagens [ref effective Java]
# Vide programação funcional: totalmente baseada na imutabilidade dos objetos
# Imutabilidade favorece concorrência
# Mas...... considerando o não-encapsulamento do python, faz sentido usar o Builder para esse propósito?
# faz sentido se preocupar com imutabilidade de objetos no Python?












