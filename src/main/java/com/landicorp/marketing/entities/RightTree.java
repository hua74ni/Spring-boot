package com.landicorp.marketing.entities;

import java.util.List;

/**
 * 菜单树
 * Created by jiangjt on 2017/9/25.
 */
public class RightTree {
    /**
     * id
     */
    private String rightId;
    /**
     * 是否张开
     */
    private boolean expand;

    /**
     * 标题
     */
    private String title;
    /**
     * 子菜单
     */
    private List<RightTree> children;

    private String hasChild;

    private String rightIcon;

    private String rightUrl;

    private boolean checked;




    private boolean open;

    private String name;

    private boolean nocheck;


    public String getRightId() {
        return rightId;
    }

    public void setRightId(String rightId) {
        this.rightId = rightId;
    }

    public boolean isExpand() {
        return expand;
    }

    public void setExpand(boolean expand) {
        this.expand = expand;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<RightTree> getChildren() {
        return children;
    }

    public void setChildren(List<RightTree> children) {
        this.children = children;
    }

    public String getHasChild() {
        return hasChild;
    }

    public void setHasChild(String hasChild) {
        this.hasChild = hasChild;
    }

    public String getRightIcon() {
        return rightIcon;
    }

    public void setRightIcon(String rightIcon) {
        this.rightIcon = rightIcon;
    }

    public String getRightUrl() {
        return rightUrl;
    }

    public void setRightUrl(String rightUrl) {
        this.rightUrl = rightUrl;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isNocheck() {
        return nocheck;
    }

    public void setNocheck(boolean nocheck) {
        this.nocheck = nocheck;
    }
}
