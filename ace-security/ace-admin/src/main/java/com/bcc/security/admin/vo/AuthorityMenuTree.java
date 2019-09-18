package com.bcc.security.admin.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.bcc.security.common.vo.TreeNode;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * ${DESCRIPTION}
 *
 * @author wanghaobin
 * @create 2017-06-19 13:03
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class AuthorityMenuTree extends TreeNode implements Serializable{

	private static final long serialVersionUID = 4419462012771944308L;
	private String text;
    private String icon;
    private List<AuthorityMenuTree> nodes = new ArrayList<AuthorityMenuTree>();
    
    public AuthorityMenuTree(String text, List<AuthorityMenuTree> nodes) {
        this.text = text;
        this.nodes = nodes;
    }

    public AuthorityMenuTree() {
    }

    @Override
    public void setChildren(List<TreeNode> children) {
        super.setChildren(children);
        nodes = new ArrayList<AuthorityMenuTree>();
    }

    @Override
    public void add(TreeNode node) {
        super.add(node);
        AuthorityMenuTree n = new AuthorityMenuTree();
        BeanUtils.copyProperties(node,n);
        nodes.add(n);
    }
}
