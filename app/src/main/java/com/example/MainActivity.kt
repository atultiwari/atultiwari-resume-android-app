package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import com.example.ui.theme.*
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AtulTiwariTheme {
                PortfolioApp()
            }
        }
    }
}

@Composable
fun PortfolioApp() {
    val scrollState = rememberScrollState()
    
    Scaffold(
        bottomBar = { BottomNavBar() },
        containerColor = MaterialTheme.colorScheme.background
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            // Futuristic Background Elements
            BackgroundGlows()

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState)
            ) {
                HeroSection()
                Spacer(modifier = Modifier.height(24.dp))
                AboutSection()
                Spacer(modifier = Modifier.height(64.dp))
                ExperienceTimeline()
                Spacer(modifier = Modifier.height(64.dp))
                ResearchAndProjects()
                Spacer(modifier = Modifier.height(64.dp))
                SkillsSection()
                Spacer(modifier = Modifier.height(64.dp))
                PublicationsSection()
                Spacer(modifier = Modifier.height(64.dp))
                SpeakerEngagementsSection()
                Spacer(modifier = Modifier.height(64.dp))
                ContactSection()
                Spacer(modifier = Modifier.height(64.dp))
            }
        }
    }
}

@Composable
fun BottomNavBar() {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = EditorialDark.copy(alpha = 0.8f),
        border = BorderStroke(1.dp, EditorialSlate800.copy(alpha = 0.5f))
    ) {
        Column(modifier = Modifier.navigationBarsPadding()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp, vertical = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                NavItem(Icons.Default.Home, "Home", true)
                NavItem(Icons.Default.Search, "Research", false)
                NavItem(Icons.Default.Campaign, "Speaking", false)
                NavItem(Icons.Default.AlternateEmail, "Contact", false)
            }
            // Android Indicator Bar
            Box(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(vertical = 12.dp)
                    .width(120.dp)
                    .height(4.dp)
                    .clip(CircleShape)
                    .background(EditorialSlate800)
            )
        }
    }
}

@Composable
fun RowScope.NavItem(icon: ImageVector, label: String, isActive: Boolean) {
    Column(
        modifier = Modifier.weight(1f),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Box(
            modifier = Modifier
                .width(56.dp)
                .height(32.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(if (isActive) EditorialCyan.copy(alpha = 0.2f) else Color.Transparent),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = if (isActive) EditorialCyan else Color.White,
                modifier = Modifier.size(20.dp)
            )
        }
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall,
            color = if (isActive) EditorialCyan else Color.White.copy(alpha = 0.6f),
            fontWeight = FontWeight.Medium
        )
    }
}

@Composable
fun BackgroundGlows() {
    val cyanGlow = EditorialCyan.copy(alpha = 0.08f)
    val purpleGlow = EditorialPurple.copy(alpha = 0.08f)
    
    Canvas(modifier = Modifier.fillMaxSize()) {
        drawCircle(
            brush = Brush.radialGradient(
                colors = listOf(cyanGlow, Color.Transparent),
                center = Offset(size.width * 1.1f, -size.height * 0.1f),
                radius = 1200f
            ),
            radius = 1200f,
            center = Offset(size.width * 1.1f, -size.height * 0.1f)
        )
        
        drawCircle(
            brush = Brush.radialGradient(
                colors = listOf(purpleGlow, Color.Transparent),
                center = Offset(-size.width * 0.1f, size.height * 0.8f),
                radius = 1500f
            ),
            radius = 1500f,
            center = Offset(-size.width * 0.1f, size.height * 0.8f)
        )
    }
}

@Composable
fun HeroSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 24.dp)
    ) {
        // Top Header Info
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = "MEDICAL INNOVATOR",
                    style = MaterialTheme.typography.labelSmall,
                    color = EditorialCyan,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 2.sp
                )
                Text(
                    text = "Dr. Atul Tiwari, MD",
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.onBackground,
                    fontWeight = FontWeight.SemiBold
                )
            }
            Box(
                modifier = Modifier
                    .size(44.dp)
                    .clip(CircleShape)
                    .border(1.dp, EditorialSlate800, CircleShape)
                    .padding(2.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(CircleShape)
                        .background(
                            Brush.linearGradient(
                                colors = listOf(EditorialCyan, EditorialPurple)
                            )
                        )
                )
            }
        }
        
        Spacer(modifier = Modifier.height(60.dp))
        
        Text(
            text = buildAnnotatedString {
                append("Bridging ")
                withStyle(SpanStyle(brush = Brush.linearGradient(listOf(EditorialCyan, EditorialPurple)))) {
                    append("Medicine")
                }
                append(" & Intelligent ")
                withStyle(SpanStyle(fontFamily = FontFamily.Serif, fontStyle = FontStyle.Italic)) {
                    append("Technology")
                }
            },
            style = MaterialTheme.typography.displayMedium,
            lineHeight = 44.sp,
            fontWeight = FontWeight.Medium,
            letterSpacing = (-1).sp
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        Text(
            text = "Associate Professor (Pathology) | AI Researcher | State Additional Nodal Officer (AI/ML) specializing in digital health and no-code AI.",
            style = MaterialTheme.typography.bodySmall,
            color = EditorialSlate400,
            lineHeight = 20.sp,
            modifier = Modifier.fillMaxWidth(0.9f)
        )
        
        Spacer(modifier = Modifier.height(32.dp))
        
        // Metric Grid
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            MetricCard(value = "14+", label = "Publications", color = EditorialCyan, modifier = Modifier.weight(1f))
            MetricCard(value = "08+", label = "AI Workshops", color = EditorialPurple, modifier = Modifier.weight(1f))
        }
    }
}

@Composable
fun MetricCard(value: String, label: String, color: Color, modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier,
        color = EditorialSlate900.copy(alpha = 0.4f),
        shape = RoundedCornerShape(24.dp),
        border = BorderStroke(1.dp, EditorialSlate800)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = value,
                style = MaterialTheme.typography.headlineMedium,
                color = color,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = label.uppercase(),
                style = MaterialTheme.typography.labelSmall,
                color = EditorialSlate400,
                letterSpacing = 1.sp
            )
        }
    }
}

@Composable
fun AboutSection() {
    SectionContainer(title = "About Me", icon = Icons.Default.PersonOutline) {
        Text(
            text = "Dedicated and accomplished Associate Professor with a strong background in Pathology, complemented by expertise in Artificial Intelligence and Bioinformatics. As a seasoned professional in the medical and scientific community, I have demonstrated a commitment to advancing both academic and technological frontiers.",
            style = MaterialTheme.typography.bodyLarge,
            lineHeight = 26.sp,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f)
        )
    }
}

@Composable
fun ExperienceTimeline() {
    SectionContainer(title = "Experience", icon = Icons.Default.History) {
        val experiences = listOf(
            TimelineItem(
                "Associate Professor (Pathology)",
                "Govt Medical College, Chittorgarh",
                "Feb 2026 - Present",
                listOf("Teaching M.B.B.S. students", "Applying innovative teaching methods")
            ),
            TimelineItem(
                "Additional Nodal Officer (AI/ML)",
                "Dept. of Medical Education, Rajasthan",
                "Feb 2026 - Present",
                listOf("Drafting and implementation of AI/ML Policy for Medical Education & Health")
            ),
            TimelineItem(
                "AI Faculty & Advisory Member",
                "DoctorsAI & NSDA",
                "Jan 2025 - Present",
                listOf("Teach doctors about application of AI in healthcare", "Real-world machine learning automation")
            ),
            TimelineItem(
                "Assistant Professor (Pathology)",
                "RVT/RNT Medical Colleges",
                "2020 - 2026",
                listOf("Developed Central Labs", "Diagnosed leukemia cases", "Organized blood donation camps")
            )
        )

        experiences.forEach { item ->
            TimelineCard(item)
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun ResearchAndProjects() {
    SectionContainer(title = "AI Projects & Innovation", icon = Icons.Outlined.Lightbulb) {
        val projects = listOf(
            ProjectItem("MedTutor AI", "AI-assisted virtual medical teaching platform for MBBS students for concept clarification.", Icons.Default.AutoStories),
            ProjectItem("MedCross AI", "AI-enabled medical crossword learning tool to reinforce high-yield concepts.", Icons.Default.Extension),
            ProjectItem("MedEval AI", "AI-assisted exam answer evaluation system providing structured feedback.", Icons.Default.FactCheck)
        )

        LazyRow(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            items(projects) { project ->
                ProjectCard(project)
            }
        }
    }
}

@Composable
fun SkillsSection() {
    SectionContainer(title = "Expertise", icon = Icons.Default.Psychology) {
        SkillGroup("Medical", listOf("Pathology", "Histopathology", "Flow Cytometry", "Hematopathology", "Cytopathology"))
        Spacer(modifier = Modifier.height(16.dp))
        SkillGroup("AI & Tech", listOf("ML", "Deep Learning", "NLP", "No-code AI Tools", "Python", "Kotlin", "Java"))
        Spacer(modifier = Modifier.height(16.dp))
        SkillGroup("Platforms", listOf("Google AI Studio", "n8n", "crewAI", "Cursor", "Orange", "Vertex AI"))
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SkillGroup(name: String, skills: List<String>) {
    Column {
        Text(text = name, style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.primary)
        Spacer(modifier = Modifier.height(8.dp))
        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            skills.forEach { skill ->
                SkillChip(skill)
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SkillChip(name: String) {
    Surface(
        color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.2f))
    ) {
        Text(
            text = name,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}

@Composable
fun PublicationsSection() {
    SectionContainer(title = "Selected Publications", icon = Icons.Default.Article) {
        val publications = listOf(
            "XAI-Driven Fine-Tuned EfficientNetV2 Model for White Blood Cell Classification (Springer, 2026)",
            "Artificial intelligence driven diabetes risk assessment with Orange (Scope, 2025)",
            "AI-assisted tuberculosis detection on chest X-rays (Int J Curr Pharm, 2025)",
            "Optimized Ensemble of Hybrid RNN-GAN Models for Lung Tumour Detection (IJACSA, 2023)"
        )
        
        publications.forEach { pub ->
            Row(modifier = Modifier.padding(vertical = 8.dp)) {
                Icon(Icons.Default.Launch, contentDescription = null, modifier = Modifier.size(18.dp), tint = MaterialTheme.colorScheme.primary)
                Spacer(modifier = Modifier.width(12.dp))
                Text(text = pub, style = MaterialTheme.typography.bodyMedium)
            }
            HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp), color = MaterialTheme.colorScheme.surfaceVariant)
        }
    }
}

@Composable
fun SpeakerEngagementsSection() {
    SectionContainer(title = "Speaker Engagements", icon = Icons.Default.Campaign) {
        val talks = listOf(
            SpeakerItem("Global RAJMEDICON-2026", "AI for Medical Teachers"),
            SpeakerItem("GAPM 2026, Ahmedabad", "AI in Laboratory Medicine"),
            SpeakerItem("Doctors AI Global Summit 2025", "Deep Learning & Generative AI in Diagnostic Medicine"),
            SpeakerItem("Emirates Pathology Conference, Dubai", "RAG in Pathology using Pediatric Tumours")
        )
        
        talks.forEach { talk ->
            Card(
                modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.5f))
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(text = talk.event, style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.primary)
                    Text(text = talk.topic, style = MaterialTheme.typography.titleMedium)
                }
            }
        }
    }
}

@Composable
fun ContactSection() {
    val uriHandler = LocalUriHandler.current
    SectionContainer(title = "Contact & Social", icon = Icons.Default.ConnectWithoutContact) {
        Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
            ContactItem(Icons.Default.Email, "atultiwari.in@gmail.com") { uriHandler.openUri("mailto:atultiwari.in@gmail.com") }
            ContactItem(Icons.Default.Language, "atultiwari.in") { uriHandler.openUri("https://atultiwari.in/") }
            ContactItem(Icons.Default.Link, "LinkedIn /dr-atul-tiwari") { uriHandler.openUri("https://www.linkedin.com/in/dr-atul-tiwari/") }
            ContactItem(Icons.Default.AlternateEmail, "Twitter @dratultiwari") { uriHandler.openUri("https://twitter.com/dratultiwari") }
        }
    }
}

@Composable
fun ContactItem(icon: ImageVector, label: String, onClick: () -> Unit) {
    Surface(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(icon, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
            Spacer(modifier = Modifier.width(16.dp))
            Text(text = label, style = MaterialTheme.typography.bodyLarge)
        }
    }
}

@Composable
fun SectionContainer(title: String, icon: ImageVector, content: @Composable () -> Unit) {
    Column(modifier = Modifier.padding(horizontal = 24.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(icon, contentDescription = null, tint = MaterialTheme.colorScheme.primary, modifier = Modifier.size(28.dp))
            Spacer(modifier = Modifier.width(12.dp))
            Text(text = title, style = MaterialTheme.typography.headlineLarge, color = MaterialTheme.colorScheme.onBackground)
        }
        Spacer(modifier = Modifier.height(24.dp))
        content()
    }
}

@Composable
fun TimelineCard(item: TimelineItem) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = EditorialSlate900.copy(alpha = 0.4f),
        shape = RoundedCornerShape(24.dp),
        border = BorderStroke(1.dp, EditorialSlate800.copy(alpha = 0.5f))
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Text(
                text = item.date,
                style = MaterialTheme.typography.labelSmall,
                color = EditorialCyan,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = item.role,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = item.institution,
                style = MaterialTheme.typography.bodySmall,
                color = EditorialSlate400
            )
            Spacer(modifier = Modifier.height(12.dp))
            item.highlights.forEach { bullet ->
                Row(modifier = Modifier.padding(bottom = 6.dp)) {
                    Text(text = "•", color = EditorialCyan, modifier = Modifier.padding(end = 8.dp))
                    Text(
                        text = bullet,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f)
                    )
                }
            }
        }
    }
}

@Composable
fun ProjectCard(project: ProjectItem) {
    Box(
        modifier = Modifier
            .width(260.dp)
            .height(160.dp)
            .drawBehind {
                drawRoundRect(
                    brush = Brush.linearGradient(listOf(EditorialCyan.copy(alpha = 0.1f), EditorialPurple.copy(alpha = 0.1f))),
                    size = size,
                    cornerRadius = androidx.compose.ui.geometry.CornerRadius(32.dp.toPx())
                )
            }
    ) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.surface.copy(alpha = 0.7f),
            shape = RoundedCornerShape(32.dp),
            border = BorderStroke(1.dp, EditorialSlate800)
        ) {
            Row(
                modifier = Modifier.padding(20.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(modifier = Modifier.weight(1f).padding(end = 12.dp)) {
                    Surface(
                        color = EditorialCyan.copy(alpha = 0.1f),
                        shape = CircleShape
                    ) {
                        Text(
                            text = "PROJECT",
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp),
                            style = MaterialTheme.typography.labelSmall,
                            fontSize = 8.sp,
                            color = EditorialCyan,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = project.title,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        text = project.desc,
                        style = MaterialTheme.typography.bodySmall,
                        color = EditorialSlate400,
                        maxLines = 2,
                        lineHeight = 14.sp
                    )
                }
                Surface(
                    modifier = Modifier.size(44.dp),
                    color = EditorialSlate800,
                    shape = RoundedCornerShape(12.dp),
                    border = BorderStroke(1.dp, EditorialSlate700)
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowOutward,
                        contentDescription = null,
                        modifier = Modifier.padding(10.dp),
                        tint = Color.White
                    )
                }
            }
        }
    }
}

@Composable
fun GlassTopBar(scrollValue: Int) {
    val alpha by animateFloatAsState(targetValue = if (scrollValue > 50) 0.8f else 0f)
    
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp)
            .background(MaterialTheme.colorScheme.surface.copy(alpha = alpha))
            .blur(if (scrollValue > 50) 10.dp else 0.dp)
            .padding(horizontal = 24.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        if (scrollValue > 500) {
            Text(
                text = "Dr. Atul Tiwari, MD",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}

data class TimelineItem(val role: String, val institution: String, val date: String, val highlights: List<String>)
data class ProjectItem(val title: String, val desc: String, val icon: ImageVector)
data class SpeakerItem(val event: String, val topic: String)
