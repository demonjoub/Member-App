package cuzhy.com.member

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import cuzhy.com.model.User
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val url = "https://reqres.in/api/users?page=1"

        AsyncTaskHandleJson().execute(url)
    }

    inner class AsyncTaskHandleJson: AsyncTask<String, String, String>() {
        override fun doInBackground(vararg url: String?): String {
            var text: String
            val connection = URL(url[0]).openConnection() as HttpURLConnection
            try {
                connection.connect()
                text = connection.inputStream.use { it.reader().use { reader -> reader.readText() } }
            } finally {
                connection.disconnect()
            }
            return text
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            handleJson(result)
        }

        private fun handleJson(jsonString: String?) {
            var response = JSONObject(jsonString)
            val userArray = JSONArray(response.getString("data"))
            val list = ArrayList<User>()

            for (i in 0 until userArray.length()) {
                var userObject = userArray.getJSONObject(i)
                list.add(
                    User(
                        userObject.getInt("id"),
                        userObject.getString("email"),
                        userObject.getString("first_name"),
                        userObject.getString("last_name"),
                        userObject.getString("avatar")
                    )
                )
            }

            val adapter = ListAdapte(this@MainActivity, list)
            user_list.adapter = adapter

            user_list.setOnItemClickListener { parent, view, position, id ->
                val myItem = parent.getItemAtPosition(position) as User
                // on click item list view
                Log.d("DEMONJOUB", id.toString() + ": " + myItem.firstName )
            }
        }
    }
}
