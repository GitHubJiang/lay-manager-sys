<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- Modal Start --> 
<!-- Modal Logout -->
<div class="md-modal md-3d-slit" id="logout-modal">
  <div class="md-content" style="height:120px;">
    <div>
      <p class="text-center">你确定退出么?</p>
      <p class="text-center">
        <button class="btn btn-default md-close">取消</button>
        <a href="${pagebase }/lout" class="btn btn-danger md-close">确定退出</a> </p>
    </div>
  </div>
</div>
<!-- Organization Picker Modal -->
<div class="md-modal md-3d-slit" id="orgpicker-modal">
  <div class="md-content" style="height:510px;">
    <h3 class="cusbg">请选择你希望操作的组织</h3>
    <input type="hidden" name="orgpicker.org.id" value=""/>
    <!-- Name 在生产环境中其实不需要，示例中使用 -->
    <input type="hidden" name="orgpicker.org.name" value=""/>
    <div class="modal-body"><div id="org-tree"></div></div>
    <div class="modal-footer">
      <button class="btn btn-default md-close pull-left">取消</button>
      <a href="javascript:;" class="btn btn-primary pull-right">确定</a> </div>
  </div>
</div>