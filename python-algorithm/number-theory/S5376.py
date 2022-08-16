import sys


input = sys.stdin.readline


def gcd(a, b):
    return b if a % b == 0 else gcd(b, a % b)


    def solution(decimal):
        if '(' in decimal:
                bracket_idx = 2
                        for data in decimal[2:]:
                                    if data == '(':
                                                    break
                                                                bracket_idx += 1

                                                                                X = '0' if decimal[2:bracket_idx] == "" else decimal[2:bracket_idx]
                                                                                        Y = decimal[bracket_idx + 1:-2]

                                                                                                        nine_len = len(Y)
                                                                                                                zero_len = len(X) if X != '0' else 0

                                                                                                                                parent = int('9' * nine_len + '0' * zero_len)
                                                                                                                                        child = int(X + Y) - int(X)

                                                                                                                                                        dividor = gcd(parent, child)

                                                                                                                                                                        print((child // dividor), "/", (parent // dividor), sep="")
                                                                                                                                                                            else:
                                                                                                                                                                                    ten_len = len(decimal) - 3
                                                                                                                                                                                            dividor = gcd(pow(10, ten_len), int(decimal[2:]))

                                                                                                                                                                                                            print((int(decimal[2:]) // dividor), "/", (pow(10, ten_len) // dividor), sep="")


                                                                                                                                                                                                                    tc = int(input())

                                                                                                                                                                                                                    for _ in range(tc):
                                                                                                                                                                                                                        decimal = input()

                                                                                                                                            solution(decimal)
