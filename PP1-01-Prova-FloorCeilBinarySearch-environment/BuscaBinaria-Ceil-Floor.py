# Dado um array ordenado de inteiros ordenado, floor(x) eh o elemento do array menor 
# (e mais proximo possível de x) ou igual a x (podendo x pertencer ou nao ao array). 
# Analogamente, ceil(x) seria o elemento do array que é maior (e mais próximo 
# possível de x) ou igual a x. Caso nao exista floor ou ceil, retorna null.
 
# Exemplo: floor([4,6,8,10],7) = 6, ceil([4,6,8,10],7) = 8,
# floor([4,6,8,10],8) = ceil([4,6,8,10],8) = 8

def floor(arr, elemento):
  return floor_rec(arr, elemento, "NULL")

def floor_rec(arr, e, maisProximo):
  if len(arr) == 0:
    return maisProximo
  index = len(arr) // 2
  if arr[index] == e:
    return e
  if arr[index] > e:
    return floor_rec(arr[: index], e, maisProximo)
  if arr[index] < e:
    return floor_rec(arr[index + 1:], e, arr[index])


print("----- FLOOR -----")
print(floor([4,6,8,10],7))
print(floor([4,6,8,10],8))
print(floor([4, 5, 6, 8, 10], 7))
print(floor([5, 9, 12, 15], 7))
print(floor([8,9,10,11], 7))


def ceil(arr, elemento):
  return ceil_rec(arr, elemento, "NULL")

def ceil_rec(arr, e, maisProximo):
  if len(arr) == 0:
    return maisProximo
  index = len(arr) // 2
  if arr[index] == e:
    return e
  if arr[index] < e:
    return ceil_rec(arr[index + 1:], e, maisProximo)
  if arr[index] > e:
    return ceil_rec(arr[:index], e, arr[index])


print("----- CEIL ------")
print(ceil([4,6,8,10],7))
print(ceil([4,6,8,10],8))
print(ceil([4, 5, 6, 8, 10], 7))
print(ceil([5, 9, 12, 15], 7))
print(ceil([1,2,3,4,5], 7))
