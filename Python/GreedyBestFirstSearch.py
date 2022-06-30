# 3 frog problem with greedy best first search

import copy


def moveleft(root):
    lst = copy.deepcopy(root)
    indexofzero = lst.index(0)
    if indexofzero != len(lst) - 1:
        lst[indexofzero + 1], lst[indexofzero] = lst[indexofzero], lst[indexofzero + 1]

        return lst

    else:
        return False

def moveright(root):
    lst = copy.deepcopy(root)
    indexofzero = lst.index(0)
    if indexofzero != 0:
        lst[indexofzero - 1], lst[indexofzero] = lst[indexofzero], lst[indexofzero - 1]

        return lst

    else:
        return False

def jumpright(root):
    lst = copy.deepcopy(root)
    indexofzero = lst.index(0)
    if indexofzero >= 2:
        lst[indexofzero - 2], lst[indexofzero] = lst[indexofzero], lst[indexofzero - 2]
        return lst

    else:
        return False


def jumpleft(root):
    lst = copy.deepcopy(root)
    indexofzero = lst.index(0)
    if indexofzero < len(lst) - 2:
        lst[indexofzero + 2], lst[indexofzero] = lst[indexofzero], lst[indexofzero + 2]
        return lst

    else:
        return False


heuristic = {
    (1, 1, 1, 0, 2, 2, 2): 24,
    (1, 1, 0, 1, 2, 2, 2): 23,
    (1, 1, 1, 2, 0, 2, 2): 23,
    (1, 0, 1, 1, 2, 2, 2): 22,
    (1, 1, 1, 2, 2, 0, 2): 22,
    (0, 1, 1, 1, 2, 2, 2): 21,
    (1, 1, 2, 1, 0, 2, 2): 21,
    (1, 1, 0, 2, 1, 2, 2): 21,
    (1, 1, 1, 2, 2, 2, 0): 21,
    (1, 1, 2, 1, 2, 0, 2): 20,
    (1, 1, 2, 0, 1, 2, 2): 20,
    (1, 1, 2, 1, 2, 2, 0): 19,
    (1, 0, 1, 2, 1, 2, 2): 20,
    (0, 1, 1, 2, 1, 2, 2): 19,
    (1, 1, 2, 0, 2, 1, 2): 18,
    (1, 0, 2, 1, 1, 2, 2): 18,
    (1, 1, 2, 2, 1, 0, 2): 18,
    (1, 2, 1, 0, 1, 2, 2): 18,
    (1, 1, 0, 2, 2, 1, 2): 19,
    (1, 1, 2, 2, 0, 1, 2): 17,
    (1, 0, 2, 1, 2, 1, 2): 16,
    (0, 1, 2, 1, 1, 2, 2): 17,
    (1, 2, 0, 1, 1, 2, 2): 17,
    (1, 1, 2, 2, 1, 2, 0): 17,
    (1, 2, 1, 1, 0, 2, 2): 19,
    (1, 2, 1, 2, 1, 0, 2): 16,
    (1, 0, 1, 2, 2, 1, 2): 18,
    (0, 1, 1, 2, 2, 1, 2): 17,
    (1, 1, 2, 2, 2, 1, 0): 16,
    (0, 1, 2, 1, 2, 1, 2): 15,
    (1, 2, 0, 1, 2, 1, 2): 15,
    (2, 1, 0, 1, 1, 2, 2): 15,
    (0, 2, 1, 1, 1, 2, 2): 15,
    (1, 1, 2, 2, 0, 2, 1): 15,
    (1, 2, 1, 1, 2, 0, 2): 18,
    (1, 2, 1, 1, 2, 2, 0): 17,
    (1, 2, 1, 2, 0, 1, 2): 15,
    (1, 2, 1, 2, 1, 2, 0): 15,
    (1, 2, 1, 0, 2, 1, 2): 16,
    (1, 1, 2, 2, 2, 0, 1): 14,
    (2, 1, 0, 1, 2, 1, 2): 13,
    (0, 2, 1, 1, 2, 1, 2): 13,
    (1, 2, 2, 1, 0, 1, 2): 13,
    (2, 0, 1, 1, 1, 2, 2): 14,
    (2, 1, 1, 0, 1, 2, 2): 16,
    (2, 1, 1, 1, 0, 2, 2): 17,
    (1, 1, 2, 0, 2, 2, 1): 16,
    (1, 1, 0, 2, 2, 2, 1): 17,
    (1, 2, 1, 2, 2, 1, 0): 13,
    (1, 2, 0, 2, 1, 1, 2): 13,
    (1, 2, 1, 2, 0, 2, 1): 13,
    (2, 1, 1, 0, 2, 1, 2): 14,
    (2, 0, 1, 1, 2, 1, 2): 12,
    (2, 1, 2, 1, 0, 1, 2): 11,
    (1, 2, 2, 1, 1, 0, 2): 14,
    (1, 2, 2, 0, 1, 1, 2): 12,
    (1, 2, 2, 1, 2, 1, 0): 11,
    (2, 1, 1, 2, 1, 0, 2): 14,
    (2, 1, 1, 1, 2, 0, 2): 16,
    (2, 1, 1, 1, 2, 2, 0): 15,
    (1, 0, 2, 1, 2, 2, 1): 14,
    (1, 0, 1, 2, 2, 2, 1): 16,
    (0, 1, 1, 2, 2, 2, 1): 15,
    (1, 2, 1, 2, 2, 0, 1): 12,
    (1, 0, 2, 2, 1, 1, 2): 14,
    (0, 2, 1, 2, 1, 1, 2): 11,
    (1, 2, 1, 0, 2, 2, 1): 14,
    (1, 2, 0, 2, 1, 2, 1): 11,
    (2, 1, 1, 2, 0, 1, 2): 13,
    (2, 1, 2, 1, 1, 0, 2): 12,
    (2, 1, 2, 0, 1, 1, 2): 10,
    (2, 1, 2, 1, 2, 1, 0): 9,
    (1, 2, 2, 1, 1, 2, 0): 13,
    (1, 2, 2, 1, 2, 0, 1): 10,
    (2, 1, 1, 2, 1, 2, 0): 13,
    (1, 2, 0, 1, 2, 2, 1): 13,
    (0, 1, 2, 1, 2, 2, 1): 13,
    (0, 1, 2, 2, 1, 1, 2): 13,
    (2, 0, 1, 2, 1, 1, 2): 10,
    (1, 2, 2, 0, 1, 2, 1): 10,
    (1, 0, 2, 2, 1, 2, 1): 12,
    (0, 2, 1, 2, 1, 2, 1): 9,
    (2, 1, 1, 2, 2, 1, 0): 11,
    (2, 1, 0, 2, 1, 1, 2): 11,
    (2, 1, 2, 1, 1, 2, 0): 11,
    (2, 0, 2, 1, 1, 1, 2): 8,
    (2, 1, 2, 1, 2, 0, 1): 8,
    (1, 2, 2, 1, 0, 2, 1): 11,
    (1, 2, 2, 0, 2, 1, 1): 8,
    (2, 1, 1, 2, 0, 2, 1): 11,
    (0, 2, 1, 1, 2, 2, 1): 11,
    (2, 1, 0, 1, 2, 2, 1): 11,
    (2, 2, 1, 0, 1, 1, 2): 8,
    (1, 2, 2, 2, 1, 0, 1): 8,
    (0, 1, 2, 2, 1, 2, 1): 11,
    (2, 0, 1, 2, 1, 2, 1): 8,
    (2, 1, 1, 2, 2, 0, 1): 10,
    (2, 1, 2, 1, 0, 2, 1): 4,
    (2, 2, 0, 1, 1, 1, 2): 7,
    (0, 2, 2, 1, 1, 1, 2): 9,
    (2, 1, 2, 0, 2, 1, 1): 6,
    (1, 2, 2, 2, 0, 1, 1): 7,
    (1, 2, 0, 2, 2, 1, 1): 9,
    (1, 0, 2, 2, 2, 1, 1): 10,
    (2, 1, 1, 0, 2, 2, 1): 12,
    (2, 1, 0, 2, 1, 2, 1): 9,
    (2, 0, 1, 1, 2, 2, 1): 10,
    (2, 2, 1, 1, 0, 1, 2): 9,
    (2, 2, 1, 1, 1, 0, 2): 10,
    (1, 2, 2, 2, 1, 1, 0): 9,
    (2, 2, 1, 0, 1, 2, 1): 6,
    (2, 1, 2, 0, 1, 2, 1): 8,
    (2, 1, 2, 2, 0, 1, 1): 5,
    (2, 1, 0, 2, 2, 1, 1): 7,
    (2, 0, 2, 1, 2, 1, 1): 4,
    (0, 2, 1, 2, 2, 1, 1): 8,
    (0, 1, 2, 2, 2, 1, 1): 9,
    (2, 2, 1, 1, 2, 1, 0): 7,
    (2, 2, 1, 1, 1, 2, 0): 9,
    (2, 2, 1, 1, 0, 2, 1): 7,
    (2, 2, 0, 1, 1, 2, 1): 5,
    (2, 2, 1, 2, 1, 0, 1): 4,
    (2, 1, 2, 2, 1, 0, 1): 8,
    (2, 0, 2, 1, 1, 2, 1): 6,
    (2, 1, 2, 2, 1, 1, 0): 3,
    (2, 0, 1, 2, 2, 1, 1): 6,
    (2, 2, 0, 1, 2, 1, 1): 3,
    (0, 2, 2, 1, 2, 1, 1): 5,
    (2, 2, 1, 1, 2, 0, 1): 6,
    (0, 2, 2, 1, 1, 2, 1): 7,
    (2, 2, 1, 2, 1, 1, 0): 5,
    (2, 2, 1, 2, 0, 1, 1): 3,
    (2, 2, 1, 0, 2, 1, 1): 4,
    (2, 2, 2, 1, 0, 1, 1): 1,
    (2, 2, 0, 2, 1, 1, 1): 1,
    (2, 2, 2, 1, 1, 0, 1): 2,
    (2, 2, 2, 0, 1, 1, 1): 0,
    (2, 2, 2, 1, 1, 1, 0): 3,
    (2, 0, 2, 2, 1, 1, 1): 2,
    (0, 2, 2, 2, 1, 1, 1): 3

}


def GreedyBFS(root, goal):
    moves = 0
    flag = True
    visited = [root]
    heuristicValues = []
    fringe = []
    while flag:
        if root == goal:
            flag = False

        leftmove = moveleft(root)
        rightmove = moveright(root)
        leftjump = jumpleft(root)
        rightjump = jumpright(root)

        if leftmove:
            if leftmove not in visited:
                fringe.append(leftmove)
        if rightmove:
            if rightmove not in visited:
                fringe.append(rightmove)
        if leftjump:
            if leftjump not in visited:
                fringe.append(leftjump)
        if rightjump:
            if rightjump not in visited:
                fringe.append(rightjump)

        for x in fringe:
            heuristicValues.append(heuristic[tuple(x)])

        miniValue = min(heuristicValues)
        miniValueIndex = heuristicValues.index(miniValue)
        heuristicValues.remove(miniValue)

        root = fringe.pop(miniValueIndex)
        visited.append(root)
        moves += 1

        heuristicValues = []
        if root == goal:
            moves += 1
            flag = False

    for x in visited:
        for y in x:
            print(y, end=" ")
        print()

    print()
    print("Nodes Visited = ", moves)

lst = [1, 1, 1, 0, 2, 2, 2]
goal = [2, 2, 2, 0, 1, 1, 1]

print("-------------------------GREEDY BEST FIRST SEARCH-------------------------------")
print()
GreedyBFS(lst, goal)
