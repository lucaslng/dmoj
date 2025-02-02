import sys
import math
sys.setrecursionlimit(10000)

n = int(input())
a = [int(input()) for _ in range(n)]

def f(i: int, j: int) -> int:
  '''How many good subarrays are there from arr[i] to arr[j]?'''

  if j - i == 1:
    return a[i] / i
  
