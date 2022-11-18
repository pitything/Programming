function delFruit(fid){
    if(confirm('是否确认删除？')){
        window.location.href='del.do?fid='+fid;
    }
}
function delFruit_mvc(fid){
    if(confirm('是否确认删除？')){
        window.location.href='fruit.do?fid='+fid+'&operate=del';
    }
}

function page(pageNo){
    window.location.href='fruit.do?pageNo=' + pageNo;
}
function page_mvc(pageNo){
    window.location.href='fruit.do?pageNo=' + pageNo;
}