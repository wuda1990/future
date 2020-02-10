class Solution:
    def divide(self, divd: int, dior: int) -> int:
        res:int = 0
        sign:int =  1 if divd ^ dior >= 0 else -1
        print(sign)
        divd = abs(divd)
        dior = abs(dior)
        while divd >= dior:
            tmp, i = dior, 1
            while divd >= tmp:
                divd -= tmp
                res += i
                if tmp < 2**29:
                    i <<= 1
                    tmp <<= 1
        if sign < 0:res = -res
        return min(max(-2**31, res), 2**31-1)

s = Solution()
print(s.divide(-18,3))

