package com.example.myapplication

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.fragment.app.FragmentContainerView
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.commit
import androidx.navigation.ui.AppBarConfiguration
import com.contentsquare.android.Contentsquare

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Contentsquare.start(this)
        setContent {
            Contentsquare.send("Home")
                Surface(modifier = Modifier.fillMaxSize()) {
                    MessageCard(Message("Android", "Jetpack Compose"))
                }

            FragmentContainer(
                modifier = Modifier.fillMaxSize(),
                fragmentManager = supportFragmentManager,
                commit = { add(it, FirstFragment()) }
            )

        }
    }

    class Message(val author: String, val body: String)

    @Composable
    fun MessageCard(msg: Message) {
        // Add padding around our message
        Row(modifier = Modifier.padding(all = 8.dp)) {
            Image(
                painter = painterResource(R.drawable.ic_launcher_foreground),
                contentDescription = "Contact profile picture",
                modifier = Modifier
                    // Set image size to 40 dp
                    .size(40.dp)
                    // Clip image to be shaped as a circle
                    .clip(CircleShape)
            )

            // Add a horizontal space between the image and the column
            Spacer(modifier = Modifier.width(8.dp))

            Column {
                ClickableText(
                    text = AnnotatedString( msg.author ),
                    onClick = {

                    }
                )

                // Add a vertical space between the author and message texts
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = msg.body)
            }
        }
    }



    @Composable
    fun FragmentContainer(
        modifier: Modifier = Modifier,
        fragmentManager: FragmentManager,
        commit: FragmentTransaction.(containerId: Int) -> Unit
    ) {
        val containerId by rememberSaveable { mutableIntStateOf(View.generateViewId()) }

        AndroidView(
            modifier = modifier,
            factory = { context ->
                fragmentManager.findFragmentById(containerId)?.view
                    ?.also { (it.parent as? ViewGroup)?.removeView(it) }
                    ?: FragmentContainerView(context)
                        .apply { id = containerId }
                        .also {
                            fragmentManager.commit { commit(it.id) }
                        }
            }
        )
    }
}