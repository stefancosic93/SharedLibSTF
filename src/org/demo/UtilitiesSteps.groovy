package org.demo
class UtilitiesSteps implements Serializable {
  def steps
  UtilitiesSteps(steps) {
    this.steps = steps
  }
  def mvn(args) {
    steps.sh "${steps.tool 'Maven'}/bin/mvn -o ${args}"
  }
}
