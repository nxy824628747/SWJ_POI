var findPoisonedDuration = function (timeSeries, duration) {
    if (timeSeries.length == 0 || duration == 0) {
        return 0;
    }
    let cur = 1;
    let re = duration;
    while (cur < timeSeries.length) {
        let time = timeSeries[cur] - timeSeries[cur - 1] + 1;
        if (timeSeries > duration) {
            re += duration;
        } else {
            re += time - 1;
        }
        cur++;
    }
    return re;
};