var Solution = function (head) {
    this.head = head;
};

/**
 * Returns a random node's value.
 * @return {number}
 */
Solution.prototype.getRandom = function () {
    let point = 1;
    let an = -1;
    let node = this.head;
    while (node != null) {
        if (an == -1) {
            an = node.val;
        } else {
            let ran = parseInt(Math.random() * (point-1));
            if (ran < 1) {
                an = node.val;
            }
        }
        node = node.next;
        point++;
    }
    return an;
};


function RandomNumBoth(Min, Max) {
    var Range = Max - Min;
    var Rand = Math.random();
    var num = Min + Math.round(Rand * Range); //四舍五入
    return num;
}