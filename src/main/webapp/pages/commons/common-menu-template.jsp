<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script id="sidebar-menu-template" type="text/x-handlebars-template">
        <ul>
            <li><a href='index.html' mid="000"><i class='icon-home-3'></i><span>仪表盘</span></a></li>
            {{#each this}}
            <li class='has_sub'><a href='javascript:void(0);'><i class='{{icon}}'></i><span>{{name}}</span> <span class="pull-right"><i class="fa fa-angle-down"></i></span></a>
                <ul>
                    {{#each submenus}}
                    <li><a href='{{url}}' mid='{{menuId}}' onclick='leftMenuClick(this)'><span>{{name}}</span></a></li>
                    {{/each}}
                </ul>
            </li>
            {{/each}}
        </ul>
</script> 

