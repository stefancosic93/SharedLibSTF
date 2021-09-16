package org.demo

def checkOutFrom(repo) {
  git url: "git@github.com:jenkinsci/${repo}"
}
