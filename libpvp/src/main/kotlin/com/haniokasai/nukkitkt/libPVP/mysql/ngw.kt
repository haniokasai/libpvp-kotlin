package com.haniokasai.nukkitkt.libPVP.mysql

/**
 * Created by hani on 2017/02/11.
 */
import cn.nukkit.Server
import cn.nukkit.event.Listener
import com.haniokasai.nukkitkt.libPVP.Main
import com.haniokasai.nukkitkt.libPVP.lang.lj

import java.sql.*
import java.util.HashMap


class mysql(/*決まり文句*/
        internal var plugin: Main) : Listener {
    companion object {


        internal var conn: Connection? = null
        var dburl =  arrayOf("jdbc:mysql://url/db?useUnicode=true&characterEncoding=UTF-8&useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "uname", "passwd")

        fun load() {
            try {
                Class.forName("com.mysql.jdbc.Driver")
                conn = DriverManager.getConnection(dburl[0],dburl[1],dburl[2])
                // ステートメントを作成
                Server.getInstance().getLogger().info("[ArrowGun] my1")
                val stmt = conn!!.createStatement()
                stmt.queryTimeout = 10
                Server.getInstance().getLogger().info("[ArrowGun]my 2")
                // INSERT
                //stmt.executeUpdate("CREATE TABLE IF NOT EXISTS gundata (gunid INT , type VARCHAR(16) ,speed INT , sleep INT , name_jpn VARCHAR(20) ,name_eng VARCHAR(20))");
                Server.getInstance().getLogger().info("[ArrowGun] my3")


                // ステートメントをクローズ
                stmt.close()
                Server.getInstance().getScheduler().scheduleRepeatingTask(Runnable { mysql.reload() }, 20 * 30)
            } catch (e: ClassNotFoundException) {
                Server.getInstance().logger.info("[ArrowGun] ClassNotFoundException" + e)
            } catch (e: Exception) {
                Server.getInstance().logger.info("[ArrowGun] Exception" + e)
            }

        }

        fun shutdown() {
            try {
                conn!!.close()
            } catch (e: SQLException) {
                e.printStackTrace()
            }

        }

        fun reload(): Boolean {
            try {
                conn!!.close()
                conn = DriverManager.getConnection(dburl[0],dburl[1],dburl[2])
            } catch (e: SQLException) {
                e.printStackTrace()
                return false
            }

            return true
        }


        fun cre8(name: String) {
            val stmt: Statement
            try {
                stmt = conn!!.createStatement()
                val sql: String
                sql = "SELECT name FROM honey WHERE name = '$name'"
                val rs = stmt.executeQuery(sql)
                var r: String? = null
                while (rs.next()) {
                    r = rs.getString("name")
                }
                if (r == null) {
                    stmt.executeUpdate("INSERT INTO honey (name, ap1,kill1,renkill1,money1,des1,gun1,gun2,gun3,gun4,gun5,gun6,gun7,gun8,gun9,gun10,flogin,llogin,hashcode,skill1,gslot1,isvip,rank,region) VALUES ('" + name + "',0,0,0,0,0,5,0,0,0,0,0,0,0,0,0," + (System.currentTimeMillis() / 1000).toInt() + "," + (System.currentTimeMillis() / 1000).toInt() + ",0,0,3,0,0," + lj.conf_region + ");")
                }
                rs.close()

                stmt.close()
            } catch (e1: SQLException) {
                e1.printStackTrace()
            }

        }

        operator fun get(name: String, kind: String): Int {
            try {
                val stmt = conn!!.createStatement()
                val sql: String
                sql = "SELECT * FROM honey WHERE name = '$name'"
                val rs = stmt.executeQuery(sql)
                var r = 0
                while (rs.next()) {
                    r = rs.getInt(kind)
                }
                rs.close()
                stmt.close()
                return r
            } catch (e: SQLException) {
                e.printStackTrace()
            }

            return 0
        }

        operator fun set(name: String, kind: String, con: Int): Boolean {
            try {
                val stmt = conn!!.createStatement()
                stmt.executeUpdate("UPDATE honey SET  $kind = $con  WHERE name = '$name'")
                stmt.close()
                return true
            } catch (e: SQLException) {
                e.printStackTrace()
                return false
            }

        }

        fun add(name: String, kind: String, con: Int): Boolean {
            try {
                val stmt = conn!!.createStatement()
                stmt.executeUpdate("UPDATE honey SET  $kind = $kind + $con  WHERE name = '$name'")
                stmt.close()
                return true
            } catch (e: SQLException) {
                e.printStackTrace()
                return false
            }

        }

        fun addap(name: String, ap: Int): Boolean {
            try {
                val stmt = conn!!.createStatement()
                stmt.executeUpdate("UPDATE honey SET  ap1 = ap1 + $ap ,money1 = money1 + $ap  WHERE name = '$name'")
                stmt.close()
                return true
            } catch (e: SQLException) {
                e.printStackTrace()
                return false
            }

        }

        fun setname(name: String, rank: String) {
            try {
                val pstmt = conn!!.prepareStatement("UPDATE honey SET  rank = ?  WHERE name = ?")
                pstmt.setString(1, rank)
                pstmt.setString(2, name)
                val rs = pstmt.executeQuery()
                rs.close()
                pstmt.close()
            } catch (e: SQLException) {
                e.printStackTrace()
            }

        }

        fun getname(name: String): String? {
            var r: String? = null
            try {
                val stmt = conn!!.createStatement()
                val rs = stmt.executeQuery("SELECT rank FROM honey WHERE name = '$name'")

                while (rs.next()) {
                    r = rs.getString("rank")
                }

                rs.close()
                stmt.close()
            } catch (e: SQLException) {
                e.printStackTrace()
            }

            return r
        }


        fun getregion(name: String): Int {
            var r = 1
            try {
                val stmt = conn!!.createStatement()
                val rs = stmt.executeQuery("SELECT region FROM honey WHERE name = '$name'")

                while (rs.next()) {
                    r = rs.getInt("region")
                }

                rs.close()
                stmt.close()
            } catch (e: SQLException) {
                e.printStackTrace()
            }

            return r
        }

        fun canchat(name: String): Boolean {
            var r = 1
            try {
                val stmt = conn!!.createStatement()
                val rs = stmt.executeQuery("SELECT chat FROM honey WHERE name = '$name'")

                while (rs.next()) {
                    r = rs.getInt("chat")
                }

                rs.close()
                stmt.close()
            } catch (e: SQLException) {
                e.printStackTrace()
            }

            if (r == 2) {
                return true
            } else {
                return false
            }
        }

        fun killed(killer: String, death: String): Boolean {
            try {
                val stmt = conn!!.createStatement()
                stmt.executeUpdate("UPDATE honey SET kill1 = kill1 + 1,renkill1 = renkill1+1 WHERE name = '$killer'")
                stmt.executeUpdate("UPDATE honey SET des1 = des1 +1,renkill1 = 0 WHERE name = '$death'")
                stmt.close()
                return true
            } catch (e: SQLException) {
                e.printStackTrace()
                return false
            }

        }

        fun getskills(name: String): HashMap<String, Int>? {
            try {
                val stmt = conn!!.createStatement()
                val sql: String
                sql = "SELECT * FROM honey WHERE name = '$name'"
                val rs = stmt.executeQuery(sql)
                val map = HashMap<String, Int>()
                while (rs.next()) {
                    map.put("skill", rs.getInt("skill1"))
                    map.put("gun1", rs.getInt("gun1"))
                    map.put("gun2", rs.getInt("gun2"))
                    map.put("gun3", rs.getInt("gun3"))
                    map.put("gun4", rs.getInt("gun4"))
                    map.put("gun5", rs.getInt("gun5"))
                    map.put("gun6", rs.getInt("gun6"))
                    map.put("gun7", rs.getInt("gun7"))
                    map.put("gun8", rs.getInt("gun8"))
                    map.put("gun9", rs.getInt("gun9"))
                    map.put("gun10", rs.getInt("gun10"))
                    map.put("gslot", rs.getInt("gslot1"))
                    map.put("money", rs.getInt("money1"))
                    map.put("kill", rs.getInt("kill1"))
                }
                rs.close()
                stmt.close()
                return map
            } catch (e: SQLException) {
                e.printStackTrace()
            }

            return null
        }

        fun usemoney(name: String, amount: Int): Boolean {
            try {
                val stmt = conn!!.createStatement()
                val sql: String
                sql = "SELECT money1,isvip FROM honey WHERE name = '$name'"
                val rs = stmt.executeQuery(sql)
                var mo = 0
                var v = 0
                while (rs.next()) {
                    mo = rs.getInt("money1")
                    v = rs.getInt("isvip")
                }
                rs.close()
                stmt.close()
                if (v > 0) {
                    return true
                } else if (mo >= amount) {
                    add(name, "money1", -amount)
                    return true
                } else {
                    return false
                }
            } catch (e: SQLException) {
                e.printStackTrace()
                return false
            }

        }

        fun isvip(name: String): Boolean {
            try {
                val stmt = conn!!.createStatement()
                val sql: String
                sql = "SELECT isvip FROM honey WHERE name = '$name'"
                val rs = stmt.executeQuery(sql)
                var mo = 0
                while (rs.next()) {
                    mo = rs.getInt("isvip")
                }
                rs.close()
                stmt.close()
                if (mo == 1 || mo == 3) {
                    return true
                } else {
                    return false
                }
            } catch (e: SQLException) {
                e.printStackTrace()
                return false
            }

        }
    }


}