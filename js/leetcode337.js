var rob = function (root) {
    Map.prototype.getOrDefault = function (key) {
        if (this.has(key)) {
            return this.get(key);
        }
        return 0;
    }
    let f = new Map();
    let t = new Map();
    search(root, f, t);
    return max(f.getOrDefault(root), t.getOrDefault(root));
};

var search = function (root, f, t) {
    if (root == undefined) {
        return;
    }
    search(root.left,f,t);
    search(root.right,f,t);
    t.set(root, root.val + f.getOrDefault(root.left) + f.getOrDefault(root.right));
    f.set(root, max(f.getOrDefault(root.left), t.getOrDefault(root.left)) + max(f.getOrDefault(root.right), t.getOrDefault(root.right)));
}

var max = function (n0, n1) {
    return n0 > n1 ? n0 : n1;
}