var splitIntoFibonacci = function(S) {
    let reArr = [];
    search(S,reArr,0);
    return reArr;
};

var search=function(s,reArr,flag){
    if(flag==s.length){
        if(reArr.length>2){
            return true;
        }
        return false;
    }
    for(let i=flag+1;i<=s.length;i++){
        let current = s.slice(flag,i);
        let arrLen = reArr.length;
        if(i-flag>1&&current.charAt(0)=='0'){
            continue;
        }
        if(arrLen>=2){
            if(Number(current)>2**31-1){continue;}
            let sum = Number(reArr[arrLen-1])+Number(reArr[arrLen-2]);
            if(sum!=Number(current)){
                continue;
            }
        }
        reArr.push(current);
        if(search(s,reArr,i)){
            return true;
        }
        reArr.pop();
    }
    return false;
}