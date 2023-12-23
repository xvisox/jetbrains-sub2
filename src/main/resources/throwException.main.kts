#!/usr/bin/env kotlin

for (i in 1..1_000_000) {
    throw Exception("oops some error")
}