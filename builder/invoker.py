


# Builder
# Encapsula o processo de criação de objetos complexos. [HEAD FIRST]
# Possibilita a criação de objetos em um processo variável, flexível e multi-passo. [HEAD FIRST]
# Possibilita a utilização de interface fluente na criação de objeto. [GUERRA]




























# Cenário específico: criação de objetos com argumentos opcionais.




class Invoker(object):
  # Invoker original: 
  # https://github.com/choreos/enactment_engine/blob/master/Commons/src/main/java/org/ow2/choreos/invoker/DefaultInvoker.java  

  def __init__(self, taskName, task, timeout, trials, pause, time_unit):
    """
      Argumentos:
        taskName - nome da tarefa 
        task - função a ser executada
        timeout - tempo limite para uma tentativa, em segundos -- int
        trials - quantidade de tentativas, em segundos -- int
        pause - pausa entre as tentativas, em segundos -- int
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

















# Solução 0
# Explosão de __init__'s.

  def __init__(self, taskName, task, timeout):
  def __init__(self, taskName, task, timeout, trials):
  def __init__(self, taskName, task, timeout, trials, pause):
  def __init__(self, taskName, task, timeout, time_unit):
  def __init__(self, taskName, task, timeout, trials, time_unit):
  def __init__(self, taskName, task, timeout, trials, pause, time_unit):





















# Solução 1
# Jogar no __init__ só o que for obrigatório, e settar o resto linha a linha

class Invoker:

  DEFAULT_TRIALS = 1
  DEFAULT_PAUSE = 0
  DEFAULT_TIME_UNIT = 'sec'

  def __init__(self, taskName, task, timeout):
    self.taskName = taskName
    self.task = task
    self.timeout = timeout
    self.trials = DEFAULT_TRIALS
    self.pause = DEFAULT_PAUSE
    self.time_unit = DEFAULT_TIME_UNIT

  def invoke(self):
    # faz de conta que tá usando timeout, trials, pause e time_unit
    try:
      return self.task()
    except:
      raise RuntimeError('%s falhou' % taskName)



















# Código cliente

task = lambda : 5*4*3*2
invoker = InvokerBuilder("fatorial", task, 1)
invoker.trials = 3
invoker.pause = 1 

















# Considerações
# muito feio, neh?
# mais algum problema?
















# Solução 2
# Traduzindo o Builder feito no Java


class InvokerBuilder(object):
  # InvokerBuilder original:
  # https://github.com/choreos/enactment_engine/blob/master/Commons/src/main/java/org/ow2/choreos/invoker/InvokerBuilder.java

  DEFAULT_TRIALS = 1
  DEFAULT_PAUSE = 0
  DEFAULT_TIME_UNIT = 'sec'

  def __init__(self, taskName, task, timeout):
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
    return Invoker(self.taskName, self.task, self.timeout, self.trials, self.pause, self.time_unit)

















# Código cliente

task = lambda : 5*4*3*2
invoker = InvokerBuilder("fatorial", task, 1).trials(3).pause(1).time_unit('min').build()



















# Considerações
# funciona, mas... será que isso é pythonico?
# aliás, interface fluente faz sentido em Python?
# Mas vamos ver outras alternativas!
























# Solução 3
# Argumentos opcionais



class Invoker(object):

  def __init__(self, taskName, task, timeout, trials=1, pause=0, time_unit='sec'):
    """
      Argumentos:
        taskName - nome da tarefa 
        task - função a ser executada
        timeout - tempo limite para uma tentativa, em segundos -- int
        trials - quantidade de tentativas, em segundos -- int (padrão é 1)
        pause - pausa entre as tentativas, em segundos -- int (padrão é 0)
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















# Código cliente

task = lambda : 5*4*3*2
invoker = InvokerBuilder("fatorial", task, 100, 2, 10)
invoker = InvokerBuilder("fatorial", task, timeout=100, trials=2, pause=10)
invoker = InvokerBuilder("fatorial", task, timeout=1, time_unit='min')













# Considerações
# Builder sumiu!
# Seria essa solução mais pythonica?
# Poder fazer "trials=2" é um recurso muito legal do python!
























# Solução 4
# kwargs!

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



























# Código cliente
invoker = Invoker("quadrado", f, 100, trials=3, pause=2)
























# Considerações
# Perda de expressividade
# documentação fica mais complicada (?)
# Parece não ter vantagem sobre os argumentos opcionais











# TODO mostrar exemplo de kargs da query do django



















# Considerações finais

# Até agora consideramos o Builder como uma forma de "instanciar o objeto de uma vez".
# Mas ele também possui uma outra motivação mais profunda, ele ajuda a criar objetos imutáveis.
# Objetos imutáveis tem suas vantagens [EFFECTIVE JAVA]:
#   Vide programação funcional: totalmente baseada na imutabilidade dos objetos.
#   Imutabilidade favorece concorrência.
# Mas...... considerando o "não-encapsulamento" do Python, faz sentido usar o Builder para esse propósito?
# Faz sentido se preocupar com imutabilidade de objetos no Python?
# TODO encontrar exemplo em que estado inicial do objeto seria inválido





















# Bibliografia
# [HEAD FIRST] Eric Freeman & Elisabeth Freeman, Cap 14. Appendix: Leftover Pattern. Em: Head First Design Patterns. O'Reilly. 2004
# [GUERRA] Eduardo Guerra, Cap 6. Estratégias de Criação de Objetos. Em: "Design Patterns com Java. Projeto Orientado a Objetos guiado por Padrões". Casa do Código. 2013
# [EFFECTIVE JAVA]






