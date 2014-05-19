


# Builder
# Encapsula o processo de cria��o de objetos complexos. [HEAD FIRST]
# Possibilita a cria��o de objetos em um processo vari�vel, flex�vel e passo-a-passo. [HEAD FIRST]
# Possibilita a utiliza��o de interface fluente na cria��o de objeto. [GUERRA]
# Em uma palavra: *flex�vel*



























# Cen�rio espec�fico: cria��o de objetos com argumentos opcionais.




class Invoker(object):
  # Invoker original: 
  # https://github.com/choreos/enactment_engine/blob/master/Commons/src/main/java/org/ow2/choreos/invoker/DefaultInvoker.java  

  def __init__(self, taskName, task, timeout, trials, pause, time_unit):
    """
      Argumentos:
        taskName - nome da tarefa 
        task - fun��o a ser executada
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
    # faz de conta que t� usando timeout, trials, pause e time_unit
    try:
      return self.task()
    except:
      raise RuntimeError('%s falhou' % taskName)

















# Solu��o 0
# Explos�o de __init__'s.

  def __init__(self, taskName, task, timeout):
  def __init__(self, taskName, task, timeout, trials):
  def __init__(self, taskName, task, timeout, trials, pause):
  def __init__(self, taskName, task, timeout, time_unit):
  def __init__(self, taskName, task, timeout, trials, time_unit):
  def __init__(self, taskName, task, timeout, trials, pause, time_unit):


# Considera��es
# F�bricas est�ticas e construtores possuem uma limita��o: eles n�o escalam bem para um grande n�mero de par�metros opcionais [EFFECTIVE JAVA]. 

# OPS! No Python vc nao pode ter varios inits!


















# Solu��o 1
# Jogar no __init__ s� o que for obrigat�rio, e settar o resto linha a linha

class Invoker:

  DEFAULT_TRIALS = 1
  DEFAULT_PAUSE = 0
  DEFAULT_TIME_UNIT = 'sec'

  def __init__(self, taskName, task, timeout):
    self.taskName = taskName
    self.task = task
    self.timeout = timeout
    self.trials = self.DEFAULT_TRIALS
    self.pause = self.DEFAULT_PAUSE
    self.time_unit = self.DEFAULT_TIME_UNIT

  def invoke(self):
    # faz de conta que t� usando timeout, trials, pause e time_unit
    try:
      return self.task()
    except:
      raise RuntimeError('%s falhou' % taskName)



















# C�digo cliente

task = lambda : 5*4*3*2
invoker = Invoker("fatorial", task, 1)
invoker.trials = 3
invoker.pause = 1 

















# Considera��es
# muito feio, neh?
# mais algum problema?

# From [EFFECTIVE JAVA]:
# Unfortunately, the JavaBeans pattern has serious disadvantages of its own.
# Because construction is split across multiple calls, **a JavaBean may be in an
# inconsistent state partway through its construction**. The class does not have
# the option of enforcing consistency merely by checking the validity of the
# construction parameters. Attempting to use an object when it's in an inconsistent
# state may cause failures that are far removed from the code containing the bug, hence
# difficult to debug. A related disadvantage is that **the JavaBeans pattern precludes
# the possibility of making a class immutable**, and requires added effort on the part 
# of the programmer to ensure thread safety.

# N�o sei se no Python faz tanto sentido se preocupar em "proteger as invariantes do objeto"
# ou se preocupar com "imutabilidade.
# Mas de qualquer forma podemos dizer que nessa solu��o n�o fica claro para o cliente como
# a classe espera que seus objetos sejam constru�dos.
# Falta expressividade.
















# Solu��o 2
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
    self.trials = self.DEFAULT_TRIALS
    self.pause = self.DEFAULT_PAUSE
    self.time_unit = self.DEFAULT_TIME_UNIT

  def set_trials(trials):
    self.trials = trials
    return self

  def set_pause(pause):
    self.pause = pause
    return self

  def set_time_unit(time_unit):
    self.time_unit = time_unit
    return self

  def build(self):
    return Invoker(self.taskName, self.task, self.timeout, self.trials, self.pause, self.time_unit)

















# C�digo cliente

task = lambda : 5*4*3*2
invoker = InvokerBuilder("fatorial", task, 1).set_trials(3).set_pause(1).set_time_unit('min').build()



















# Considera��es
# funciona, mas... ser� que isso � pythonico?
# ali�s, interface fluente faz sentido em Python?
# Mas vamos ver outras alternativas!

# From [EFFECTIVE JAVA]:
# The client code is easy to write, and more importantly, to read.
# **The Builder pattern simulates named optional parameters**
# as found in Ada and Python. 
# Like a constructor, a builder can impose invariants on its parameters.


























# Solu��o 3
# Argumentos opcionais



class Invoker(object):

  # sera q daria pra usar constantes no init?
  # help(Invoker) jah mostraria assinatura do metodo com padroes
  # nao precisa colocar padroes no docstring
  
  def __init__(self, taskName, task, timeout, trials=1, pause=0, time_unit='sec'):
    """
      Argumentos:
        taskName - nome da tarefa 
        task - fun��o a ser executada
        timeout - tempo limite para uma tentativa, em segundos -- int
        trials - quantidade de tentativas, em segundos -- int (padr�o � 1)
        pause - pausa entre as tentativas, em segundos -- int (padr�o � 0)
        time_unit - string in ['sec', 'min'] (padr�o � 'sec')
    """
    self.taskName = taskName
    self.task = task
    self.timeout = timeout
    self.trials = trials
    self.pause = pause
    self.time_unit = time_unit

  def invoke(self):
    # faz de conta que t� usando timeout, trials, e pause
    try:
      return self.task()
    except:
      raise RuntimeError('%s falhou' % taskName)















# C�digo cliente

task = lambda : 5*4*3*2
invoker = InvokerBuilder("fatorial", task, 100, 2, 10)
invoker = InvokerBuilder("fatorial", task, timeout=100, trials=2, pause=10)
invoker = InvokerBuilder("fatorial", task, timeout=1, time_unit='min')













# Considera��es
# Builder sumiu!
# Seria essa solu��o mais pythonica?
# Poder fazer "trials=2" � um recurso muito legal do python!
























# Solu��o 4
# kwargs!

class Invoker(object):

  DEFAULT_TRIALS = 1
  DEFAULT_PAUSE = 0
  DEFAULT_TIME_UNIT = 'sec'

  def __init__(self, taskName, task, timeout, **kwargs):
    """
      Argumentos:
        taskName - nome da tarefa -- string
        task - tarefa a ser executada -- uma fun��o
        timeout - tempo limite para uma tentatvia em segundos -- int
        kwargs:
            trials - quantidade de tentativas em segundos -- int (padr�o � 1)
            pause - pausa entre as tentativas em segundos -- int (padr�o � 0)
            time_unit - string \in {'sec', 'min'} (padr�o � 'sec')
    """
    self.taskName = taskName
    self.task = task
    self.timeout = timeout
    self.trials = kwargs.pop('trials', DEFAULT_TRIALS)
    self.pause = kwargs.pop('pause', DEFAULT_PAUSE)
    self.time_unit = kwargs.pop('time_unit', DEFAULT_TIME_UNIT)
    if kwargs:
        raise ValueError("!!!")

  def invoke(self):
    # faz de conta que t� usando timeout, trials, e pause
    try:
      return self.task()
    except:
      raise RuntimeError('%s falhou' % taskName)



























# C�digo cliente
task = lambda : 5*4*3*2
invoker = Invoker("fat", task, 100, trials=3, pause=2)

dic = {'trials':3, 'pause':2}
invoker = Invoker("fat", task, 100, **dic)






















# Considera��es
# Perda de expressividade
# Documenta��o fica mais complicada (?)
# Cliente ficou igual a solu��o anterior
# Parece n�o ter vantagem sobre os argumentos opcionais (nesse caso...)
















# Mas h� casos em que kwargs t�m vantagem sobre argumentos opcionais.
# Situa��o: quando o nome do par�metro � vari�vel!
# Exemplo: constru��o de queries no Django.
# Exemplos retirados do Radar Parlamentar: https://github.com/leonardofl/radar_parlamentar


Parlamentar.objects.filter(nome=nome_dep)

legs = Legislatura.objects.filter(parlamentar__nome=nome_parlamentar)

Partido.objects.filter(legislatura__casa_legislativa=self).distinct()

votacoes = votacoes.filter(data__gte=ini)

CasaLegislativa.objects.filter(nome_curto='cdep').count()

Legislaturas.objects.filter(partido=partido).select_related('id', 'localidade', 'partido__nome','parlamentar__nome')

Votacao.objects.filter(proposicao__casa_legislativa=casa).filter(data__lte=self.fim).order_by('data')



# Al�m de usar o kwargs, a constru��o de queries do Django parece ser um bom exemplo de Builder.
# Uma pequena diferen�a para o padr�o: n�o usa o m�todo "build()" no final!






























# Bibliografia
# [HEAD FIRST] Eric Freeman & Elisabeth Freeman, Cap 14. Appendix: Leftover Pattern. Em: Head First Design Patterns. O'Reilly. 2004
# [GUERRA] Eduardo Guerra, Cap 6. Estrat�gias de Cria��o de Objetos. Em: "Design Patterns com Java. Projeto Orientado a Objetos guiado por Padr�es". Casa do C�digo. 2013
# [EFFECTIVE JAVA] Joshua Bloch, Cap 2. Creating and Destroying Objects. Em: "Effective Java", 2nd edition. Addison Wesley. 2008






