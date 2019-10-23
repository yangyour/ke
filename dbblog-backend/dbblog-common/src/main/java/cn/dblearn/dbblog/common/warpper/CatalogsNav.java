package cn.dblearn.dbblog.common.warpper;

import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 品目导航
 * @author developer001
 */
@ApiModel(value="品目导航")
public class CatalogsNav implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value="集采")
	private List<TreeNode> collects;

	@ApiModelProperty(value="限额集采A(5万)")
	private List<TreeNode> nonCollectsAth;

	@ApiModelProperty(value="限额集采B(20万)")
	private List<TreeNode> nonCollectsBth;

    @ApiModelProperty(value="所有品目")
    private List<TreeNode> catalogs;

	public List<TreeNode> getCollects() {
		return collects;
	}

	public void setCollects(List<TreeNode> collects) {
		this.collects = collects;
	}

	public List<TreeNode> getNonCollectsAth() {
		return nonCollectsAth;
	}

	public void setNonCollectsAth(List<TreeNode> nonCollectsAth) {
		this.nonCollectsAth = nonCollectsAth;
	}

	public List<TreeNode> getNonCollectsBth() {
		return nonCollectsBth;
	}

	public void setNonCollectsBth(List<TreeNode> nonCollectsBth) {
		this.nonCollectsBth = nonCollectsBth;
	}

    public List<TreeNode> getCatalogs() {
        return catalogs;
    }

    public void setCatalogs(List<TreeNode> catalogs) {
        this.catalogs = catalogs;
    }

}
