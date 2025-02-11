# https://dmoj.ca/problem/ccc05j5

def is_a_word(word: str) -> bool:
  if not word:
    return False
  if word == "A":
    return True
  if word.startswith("B") and word.endswith("S"):
    return is_monkey_word(word[1:-1])
  return False


def is_monkey_word(word: str) -> bool:
  if is_a_word(word):
    return True
  for i in range(1, len(word)):
    if word[i] == 'N':
      if is_a_word(word[:i]) and is_monkey_word(word[i + 1:]):
        return True
  return False


def main():
  while (word := input()) != "X":
    print("YES" if is_monkey_word(word) else "NO")


main()
