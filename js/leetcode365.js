var canMeasureWater = function (x, y, z) {
    let source = [[0, 0]];
    let set = new Set();
    while (source.length) {
        let currLen = source.length;
        let stack = [];
        while (currLen--) {
            let curr = source.shift();
            //注意 has 的值
            if (set.has(curr + '')) continue;
            set.add(curr + '');
            let currX = curr[0];
            let currY = curr[1];
            if (currX == z || currY == z || currX + currY == z) return true;
            if (currX == 0) stack.push([x, currY]);
            if (currY == 0) stack.push([currX, y]);
            if (currX == x) stack.push([0, currY]);
            if (currY == y) stack.push([currX, 0]);
            if (currX >= y - currY) stack.push([currX - y + currY, y]);
            if (currY >= x - currX) stack.push([x, currY - x + currX]);
            if (currX > 0 && currX < y - currY) stack.push([0, currX + currY]);
            if (currY > 0 && currY < x - currX) stack.push([currX + currY, 0]);
        }
        source = stack.slice(0);
    }
    return false;
};


