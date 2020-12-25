package com.example.myapplication.modle

data class Articles(
    val curPage: Int,
    val offset: Int,
    val pageCount: Int,
    val size: Int,
    val total: Int,
    val over: Boolean,
    val datas: List<ArticleData>
) {
    override fun toString(): String {
        return "Articles(curPage=$curPage, offset=$offset, pageCount=$pageCount, size=$size, " +
                "total=$total, over=$over, datas=$datas)"
    }
}

data class ArticleData(
    val niceDate: String,
    val title: String,
    val desc: String,
    val author: String,
    val shareUser: String,
    val id: Int,
    val chapterName: String,
    val link: String
) {
    override fun toString(): String {
        return "DataBean(niceDate='$niceDate', title='$title', desc='$desc', author='$author'," +
                " shareUser='$shareUser', id=$id, chapterName='$chapterName', link='$link')"
    }
}