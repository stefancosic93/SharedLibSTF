package org.demo
class Utilities implements Serializable {
  def env
  def steps
  Utilities(env, steps) {
    this.env = env
    this.steps = steps
  }

  def mvn(args) {
    steps.sh "${steps.tool 'Maven'}/bin/mvn -o ${args}"
  }
  
  def fun1() {
    println('Fun1 invoked from shared lib')
  }
  
  def fun2(number) {
    return 'Fun2 invoked from shared lib with the number:  ${number}'
  }
}
