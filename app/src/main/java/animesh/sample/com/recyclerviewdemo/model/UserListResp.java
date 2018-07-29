package animesh.sample.com.recyclerviewdemo.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UserListResp {

    @SerializedName("page")
    private int page;

    @SerializedName("per_page")
    private int perPage;

    @SerializedName("total")
    private int total;

    @SerializedName("total_pages")
    private int totalPages;

    @SerializedName("data")
    private List<DataList> data = null;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPerPage() {
        return perPage;
    }

    public void setPerPage(Integer perPage) {
        this.perPage = perPage;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public List<DataList> getData() {
        return data;
    }

    public void setData(List<DataList> data) {
        this.data = data;
    }

}


