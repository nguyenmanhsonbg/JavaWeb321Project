/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

    function renderPager(id, pageindex, totalpage, gap)
            {
                var container = document.getElementById(id);
                if (pageindex > gap + 1)
                {
                    container.innerHTML += "<a href='Order?page=1'>First</a>"
                }

                for (var i = pageindex - gap; i < pageindex; i++)
                {
                    if (i >= 1)
                    {
                        container.innerHTML += "<a href='Order?page=" + i + "'>" + i + "</a>"
                    }
                }

                container.innerHTML += "<span>" + pageindex + "</span>"

                for (var i = pageindex + 1; i <= pageindex + gap; i++)
                {
                    if (i <= totalpage)
                    {
                        container.innerHTML += "<a href='Order?page=" + i + "'>" + i + "</a>"
                    }
                }

                if (pageindex < totalpage - gap)
                {
                    container.innerHTML += "<a href='Order?page=" + totalpage + "'>Last</a>"
                }
            }
            
       
            

 