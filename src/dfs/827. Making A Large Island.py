from typing import List

class Solution:
    def largestIsland(self, grid: List[List[int]]) -> int:
        n = len(grid)
        parent = list(range(n * n))

        def find(x):
            if parent[x] != x:
                parent[x] = find(parent[x])
            return parent[x]

        def union(x, y):
            px, py = find(x), find(y)
            if px != py:
                parent[py] = px

        def index(i, j):
            return i * n + j

        # Step 1: Union adjacent 1s
        for i in range(n):
            for j in range(n):
                if grid[i][j] == 1:
                    for di, dj in [(1,0), (0,1)]:
                        ni, nj = i + di, j + dj
                        if 0 <= ni < n and 0 <= nj < n and grid[ni][nj] == 1:
                            union(index(i, j), index(ni, nj))

        # Step 2: Count sizes of each component
        parent_size = {}
        for i in range(n):
            for j in range(n):
                if grid[i][j] == 1:
                    p = find(index(i, j))
                    parent_size[p] = parent_size.get(p, 0) + 1

        # Step 3: Get max existing island
        res = max(parent_size.values(), default=0)

        # Step 4: Try flipping each 0
        for i in range(n):
            for j in range(n):
                if grid[i][j] == 0:
                    seen = set()
                    curr = 1

                    for di, dj in [(1,0), (-1,0), (0,1), (0,-1)]:
                        ni, nj = i + di, j + dj
                        if 0 <= ni < n and 0 <= nj < n and grid[ni][nj] == 1:
                            p = find(index(ni, nj))
                            if p not in seen:
                                seen.add(p)
                                curr += parent_size[p]

                    res = max(res, curr)

        return res