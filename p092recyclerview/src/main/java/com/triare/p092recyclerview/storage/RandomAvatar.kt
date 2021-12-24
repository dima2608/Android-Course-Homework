package com.triare.p092recyclerview.storage

object RandomAvatar {
    private val avatarLink = listOf(
        "https://1.bp.blogspot.com/-wcGBBlbjSgY/XyOHzJP5hmI/AAAAAAAAFXA/pj-_e6iUCYUXaBqFYFnaLBJ2Z3-A6hz3ACLcBGAsYHQ/s750/2.jpg",
        "https://i.pinimg.com/564x/98/f7/5a/98f75a01cc3fda5835e7e0195778985a.jpg",
        "https://i.pinimg.com/564x/c6/11/ec/c611ecaeed1b4bbbcb3b64064d705454.jpg",
        "https://i.pinimg.com/564x/d2/5b/fb/d25bfb232826db6295184e170459eb3c.jpg",
        "https://i.pinimg.com/564x/4a/e1/90/4ae190806d42f65085d7745fe4dba23a.jpg",
        "https://i.pinimg.com/564x/f9/5c/28/f95c2853a6df08cc4ba361fc91897a5d.jpg",
        "https://i.pinimg.com/564x/9b/87/90/9b87903d85591f0762dc5c952840c064.jpg",
        "https://i.pinimg.com/564x/ac/d5/80/acd580143e0c84e241c209bbc5402d18.jpg",
        "https://i.pinimg.com/564x/a8/89/e3/a889e3faf12a49b21bcb4560e4e180d9.jpg",
        "https://i.pinimg.com/564x/f5/3f/93/f53f93d6ad5364124c33efb1ae59b1ca.jpg"
    )

    fun randomAvatar() = avatarLink.random()
}