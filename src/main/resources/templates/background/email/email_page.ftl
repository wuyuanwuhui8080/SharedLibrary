<div class="btn-group btn-group-justified" role="group">
    <div class="btn-group" role="group">
        <div dir="ltr" style="text-align:center">第 ${page.current} 页 | 共 ${page.total} 条 | 共 ${page.pages} 页　</div>
    </div>
</div>
<div class="btn-group btn-group-justified"  data-toggle="tooltip" data-placement="top" role="group">
    <div class="btn-group" role="group">
        <button class="btn btn-white btn-sm" id="page-left"
                onclick="Left_page(${page.current}-1)">
            <i class="fa fa-arrow-left"></i></button>
    </div>
    <div class="btn-group"  data-toggle="tooltip" data-placement="top" role="group">
        <button class="btn btn-white btn-sm" id="page-right"
                onclick="Right_page(${page.current}+1)">
            <i class="fa fa-arrow-right"></i></button>
    </div>
</div>