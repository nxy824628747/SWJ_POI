var findRadius = function (houses, heaters) {
    if (heaters.length == 0 || houses.length == 0) {
        return 0;
    }
    let f = (a, b) => {
        return a - b;
    };
    houses.sort(f);
    heaters.sort(f);
    let point = 0;
    let max = 0;
    for (let i = 0; i < heaters.length; i++) {
        for (let j = point; j < houses.length; j++) {
            if (houses[j] == heaters[i]) {
                heaters[i] = j;
                break;
            }
        }
        if (i != 0) {
            let cur = heaters[i] - heaters[i - 1];
            max = max > cur ? max : cur;
        }
    }
    let start = heaters[0] - 0;
    let end = houses.length - 1 - heaters[heaters.length - 1];
    max = max > start ? max : start;
    max = max > end ? max : end;
    let re = max % 2;
    max = re == 0 ? max / 2 : Math.floor(max / 2) + 1;
    return re;
};