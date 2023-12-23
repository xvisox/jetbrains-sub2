#!/usr/bin/env kotlin

for (i in 1..20) {
    Thread.sleep(500)
    println("Finished $i out of 20")
}