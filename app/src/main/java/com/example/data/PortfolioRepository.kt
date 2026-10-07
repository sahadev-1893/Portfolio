package com.example.data

import com.example.model.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

object PortfolioRepository {

    // ==========================================
    // DEVELOPER PROFILE CONFIGURATION
    // Change this single object to rebrand the entire portfolio!
    // ==========================================
    val profile = DeveloperProfile(
        name = "Alex Vance",
        initials = "AV",
        jobTitle = "FULL-STACK DEVELOPER",
        availabilityStatus = "AVAILABLE FOR CONTRACT & FULL-TIME",
        bioSummary = "I build scalable, modern and user-focused digital experiences.",
        bioExtended = "Hello! I'm Alex Vance, a full-stack developer passionate about creating scalable web and mobile applications. My goal is to write maintainable, clean and understandable code and create products that are fast, reliable and enjoyable to use.",
        goalStatement = "My goal is to write maintainable, clean and understandable code and create products that are fast, reliable and enjoyable to use.",
        experienceYears = "5+",
        completedProjectsCount = "24+",
        publishedArticlesCount = "12+",
        clientSatisfaction = "99.8%",
        location = "San Francisco, CA (Open to Remote)",
        currentRole = "Senior Full-Stack Engineer @ Apex Labs",
        email = "alex.vance.dev@gmail.com",
        phone = "+1 (555) 382-9014",
        githubUrl = "https://github.com",
        linkedinUrl = "https://linkedin.com",
        telegramUrl = "https://t.me",
        instagramUrl = "https://instagram.com",
        facebookUrl = "https://facebook.com",
        xUrl = "https://x.com",
        resumeUrl = "https://example.com/alex_vance_fullstack_resume.pdf"
    )

    // ==========================================
    // SKILL CATEGORIES (FROM USER PROMPT)
    // ==========================================
    val skillCategories = listOf(
        SkillCategory(
            categoryName = "FRONT-END",
            skills = listOf(
                SkillItem("TypeScript", 95, "5 yrs"),
                SkillItem("JavaScript", 98, "6 yrs"),
                SkillItem("React", 96, "5 yrs"),
                SkillItem("React Native", 90, "4 yrs"),
                SkillItem("Vue", 86, "3 yrs"),
                SkillItem("Next.js", 94, "4 yrs"),
                SkillItem("Nuxt", 84, "3 yrs"),
                SkillItem("Redux Toolkit", 92, "4 yrs"),
                SkillItem("HTML", 99, "6 yrs"),
                SkillItem("CSS", 95, "6 yrs")
            )
        ),
        SkillCategory(
            categoryName = "BACK-END",
            skills = listOf(
                SkillItem("Node.js", 95, "5 yrs"),
                SkillItem("Golang", 88, "3 yrs"),
                SkillItem("Python", 89, "4 yrs"),
                SkillItem(".NET", 85, "3 yrs"),
                SkillItem("ASP.NET", 86, "3 yrs"),
                SkillItem("REST API", 98, "5 yrs"),
                SkillItem("PostgreSQL", 93, "4 yrs"),
                SkillItem("MySQL", 91, "5 yrs"),
                SkillItem("MongoDB", 90, "4 yrs"),
                SkillItem("Redis", 88, "3 yrs")
            )
        ),
        SkillCategory(
            categoryName = "DEVOPS",
            skills = listOf(
                SkillItem("Docker", 94, "4 yrs"),
                SkillItem("Git", 98, "6 yrs"),
                SkillItem("GitHub", 98, "6 yrs"),
                SkillItem("CI/CD", 90, "4 yrs"),
                SkillItem("Linux", 92, "5 yrs"),
                SkillItem("Nginx", 87, "3 yrs"),
                SkillItem("Kubernetes", 80, "2 yrs")
            )
        ),
        SkillCategory(
            categoryName = "TOOLS",
            skills = listOf(
                SkillItem("VS Code", 98, "6 yrs"),
                SkillItem("Visual Studio", 88, "4 yrs"),
                SkillItem("Postman", 96, "5 yrs"),
                SkillItem("Figma", 90, "4 yrs"),
                SkillItem("Firebase", 92, "4 yrs"),
                SkillItem("Android Studio", 89, "3 yrs")
            )
        )
    )

    // ==========================================
    // WORK EXPERIENCE TIMELINE (FROM USER PROMPT)
    // ==========================================
    val experiences = listOf(
        Experience(
            id = "exp_1",
            yearRange = "2025 - Present",
            duration = "Ongoing",
            company = "Apex Digital Labs",
            position = "Full Stack Developer",
            location = "San Francisco, CA (Remote)",
            description = "Architecting distributed cloud services and reactive web platforms serving over 450,000 monthly active users.",
            achievements = listOf(
                "Designed low-latency microservices with Golang, Kafka, and Redis reducing API response times by 42%.",
                "Spearheaded migration of legacy monolith to Next.js and containerized Kubernetes pods.",
                "Engineered CI/CD automated pipeline with GitHub Actions reducing deployment cycles to under 6 minutes."
            ),
            technologies = listOf("React", "Node.js", "Golang", "PostgreSQL", "Docker", "Kafka")
        ),
        Experience(
            id = "exp_2",
            yearRange = "2023 - 2025",
            duration = "2 Years",
            company = "XYZ Solutions",
            position = "Software Developer",
            location = "Austin, TX",
            description = "Led frontend architecture and backend API integrations across enterprise CRM and data visualization systems.",
            achievements = listOf(
                "Built mission-critical administrative dashboards using React, TypeScript, and .NET Core REST APIs.",
                "Optimized SQL Server stored procedures and database indexes yielding 3x throughput improvement.",
                "Integrated secure OAuth2 authentication and role-based access control across 12 services."
            ),
            technologies = listOf("React", ".NET", "SQL Server", "TypeScript", "Redis")
        ),
        Experience(
            id = "exp_3",
            yearRange = "2021 - 2023",
            duration = "2 Years",
            company = "ABC Technologies",
            position = "Full Stack Engineer",
            location = "Boston, MA",
            description = "Developed customer-facing web applications and cross-platform mobile prototypes.",
            achievements = listOf(
                "Built React Native and Node.js applications with offline sync capabilities.",
                "Implemented real-time WebSocket messaging and automated test suites with 92% coverage.",
                "Collaborated closely with product designers using Figma design systems to deliver pixel-perfect UI."
            ),
            technologies = listOf("React", "Node.js", "PostgreSQL", "React Native", "TailwindCSS")
        ),
        Experience(
            id = "exp_4",
            yearRange = "2019 - 2021",
            duration = "2 Years",
            company = "Quantum Software Works",
            position = "Junior Software Developer",
            location = "Remote",
            description = "Started career contributing to responsive frontend components, RESTful endpoints, and automated tests.",
            achievements = listOf(
                "Refactored legacy vanilla JavaScript to modern Vue and TypeScript.",
                "Authored 50+ unit and integration test suites using Jest and Cypress.",
                "Maintained Dockerized local development environments for engineering team."
            ),
            technologies = listOf("JavaScript", "Vue", "REST API", "Docker", "MySQL")
        )
    )

    // ==========================================
    // PROJECTS SHOWCASE (FROM USER PROMPT)
    // ==========================================
    val projects = listOf(
        Project(
            id = "proj_01",
            number = "PROJECT 01",
            title = "Portfolio Website",
            subtitle = "Modern Developer Portfolio & Editorial Showcase",
            category = "Full-Stack",
            description = "A modern personal portfolio platform designed to showcase development experience, projects and technical skills.",
            overview = "Engineered with a luxury dark aesthetic, bold editorial typography, and high-performance server-side rendering. Features an interactive project explorer, live article reader, and streamlined contact workflow.",
            problem = "Traditional developer portfolios often feel cluttered, generic, or slow, failing to communicate both aesthetic sensibility and engineering rigor.",
            solution = "Created an ultra-minimalist, editorial dark-mode application featuring custom typography pairing (Space Grotesk & JetBrains Mono), responsive layout grids, and optimized client-side state caching.",
            features = listOf(
                "Responsive dark-first editorial layout with custom geometric accents",
                "Centralized dynamic configuration enabling effortless rebranding",
                "Full-text article reader with syntax-highlighted code inspection",
                "Interactive project showcase with metrics, challenges and GitHub/Demo links",
                "Zero-lag local state persistence and smooth animated transitions"
            ),
            technologies = listOf("React", "TypeScript", "Next.js", "PostgreSQL", "TailwindCSS"),
            challenges = "Balancing bold visual minimalism and heavy typography without hurting page performance and responsive mobile ergonomics.",
            results = "Achieved 100/100 Lighthouse performance score, under 0.8s time-to-interactive, and featured across multiple developer design showcases.",
            githubUrl = "https://github.com/developer/portfolio-website",
            liveDemoUrl = "https://portfolio.dev-preview.app",
            keyMetrics = listOf("100%" to "Lighthouse Score", "<0.8s" to "TTI", "0" to "Dependencies Overhead"),
            isFeatured = true,
            visualAccentHex = 0xFFFFFFFF
        ),
        Project(
            id = "proj_02",
            number = "PROJECT 02",
            title = "Mobile Learning App",
            subtitle = "Interactive Micro-Course & Skill Acceleration Platform",
            category = "Mobile",
            description = "Cross-platform mobile education app featuring bite-sized interactive engineering lessons and code playgrounds.",
            overview = "Designed for engineers on the go. Offers offline-first study materials, gamified progress streaks, code syntax challenges, and real-time cloud sync.",
            problem = "Technical learning resources are heavily geared towards desktop IDEs, making mobile micro-learning cumbersome and impractical.",
            solution = "Developed an ergonomic mobile interface with gesture-based quiz interactions, optimized monospace mobile code snippets, and background sync using Redux Toolkit and Firebase.",
            features = listOf(
                "Bite-sized engineering challenges with instant validation",
                "Offline lesson caching and optimistic background synchronization",
                "Custom mobile code viewer with zoom and copy support",
                "Gamified learning streaks and achievement badges",
                "Cross-device progress syncing via Firebase Auth & Firestore"
            ),
            technologies = listOf("React Native", "TypeScript", "Redux Toolkit", "Firebase", "Jest"),
            challenges = "Building smooth code syntax highlighting on low-spec mobile devices without UI thread stutter.",
            results = "Over 35,000 downloads, 4.8-star App Store rating, and an average user retention of 68% after 30 days.",
            githubUrl = "https://github.com/developer/mobile-learning-app",
            liveDemoUrl = "https://learning-app.dev-preview.app",
            keyMetrics = listOf("35k+" to "Active Students", "4.8★" to "Rating", "99.9%" to "Crash-Free Rate"),
            isFeatured = true,
            visualAccentHex = 0xFF38BDF8
        ),
        Project(
            id = "proj_03",
            number = "PROJECT 03",
            title = "Grievance Management System",
            subtitle = "Enterprise Ticket Routing & Resolution Engine",
            category = "Full-Stack",
            description = "A comprehensive enterprise grievance tracking platform featuring automated priority SLA queues, auditable logs, and stakeholder portals.",
            overview = "Engineered for institutional governance. Replaces chaotic email threads and paper forms with an automated, role-gated dispute resolution system.",
            problem = "Public and private institutions lose weeks sorting through untracked disputes, resulting in compliance failures and dissatisfied constituents.",
            solution = "Architected a high-throughput Blazor WebAssembly frontend paired with C# .NET Core and PostgreSQL, offering real-time escalation triggers and end-to-end cryptographic audit trails.",
            features = listOf(
                "Multi-tiered role-based access control (Admin, Officer, User)",
                "Automated SLA breach warning triggers and escalations",
                "Cryptographically signed immutable audit timeline logs",
                "Encrypted PDF document upload and virus scanning integration",
                "Real-time analytics dashboard with resolution turnaround metrics"
            ),
            technologies = listOf("Blazor", "C#", ".NET Core", "PostgreSQL", "REST API", "Docker"),
            challenges = "Meeting stringent enterprise security and data privacy mandates while maintaining sub-second query speeds across millions of grievance records.",
            results = "Reduced average dispute resolution time from 18 days to 4.2 days across 4 enterprise deployments.",
            githubUrl = "https://github.com/developer/grievance-management",
            liveDemoUrl = "https://grievance-demo.enterprise.app",
            keyMetrics = listOf("76%" to "Resolution Speedup", "100%" to "Audit Compliance", "1.2M+" to "Processed Records"),
            isFeatured = false,
            visualAccentHex = 0xFF10B981
        ),
        Project(
            id = "proj_04",
            number = "PROJECT 04",
            title = "College Management System",
            subtitle = "Academic Administration & Student Portal Ecosystem",
            category = "Web",
            description = "Unified higher-education portal for course scheduling, grades, attendance tracking, and faculty collaboration.",
            overview = "Consolidates course registrations, semester exams, faculty rosters, and fee payment tracking into an intuitive single-pane experience.",
            problem = "Universities suffer from fragmented legacy software, where admissions, grades, and tuition live in disconnected databases.",
            solution = "Created a unified React application backed by a modular Node.js REST API with PostgreSQL connection pooling and Redis session caching.",
            features = listOf(
                "Real-time course seat registration with concurrency locking",
                "Interactive GPA calculator and official grade sheet generator",
                "Faculty grade submission workflow with supervisor approvals",
                "Student timetable calendar with Google Calendar sync",
                "Automated attendance low-threshold notification alerts"
            ),
            technologies = listOf("React", "Node.js", "PostgreSQL", "Redis", "Docker", "TailwindCSS"),
            challenges = "Handling extreme traffic spikes during initial course enrollment registration windows without database lockups.",
            results = "Deployed at 3 regional collegiate departments serving 14,000+ students with zero downtime during peak registration weeks.",
            githubUrl = "https://github.com/developer/college-management-system",
            liveDemoUrl = "https://college-portal.demo.app",
            keyMetrics = listOf("14k+" to "Enrolled Students", "0s" to "Downtime Peak Week", "98%" to "Paper Reduction"),
            isFeatured = false,
            visualAccentHex = 0xFFF59E0B
        ),
        Project(
            id = "proj_05",
            number = "PROJECT 05",
            title = "E-Commerce Platform",
            subtitle = "High-Conversion Modular Storefront & Checkout",
            category = "Full-Stack",
            description = "Modern headless e-commerce store with real-time inventory management, Stripe payment processing, and admin analytics.",
            overview = "Built for rapid direct-to-consumer merchandising. Combines server-side rendered storefronts with lightning-fast cart operations and robust inventory sync.",
            problem = "Standard off-the-shelf platforms are bloated, impose heavy subscription transaction fees, and suffer from poor mobile loading speeds.",
            solution = "Developed a lightweight headless architecture utilizing Next.js for ISR storefront pages, MongoDB for flexible product catalogs, and Stripe Elements for frictionless PCI-compliant payments.",
            features = listOf(
                "Headless shopping cart with instant optimistic UI updates",
                "Stripe Elements multi-currency checkout & Apple Pay support",
                "Admin inventory manager with low-stock webhooks",
                "Algolia-powered instant product search with fuzzy matching",
                "Automated customer order tracking email dispatch via SendGrid"
            ),
            technologies = listOf("Next.js", "Node.js", "MongoDB", "Stripe", "Redis", "TypeScript"),
            challenges = "Preventing overselling race conditions during flash promotional sales with simultaneous checkout sessions.",
            results = "Average page load of 420ms, conversion rate increase of 28%, and over $450k in processed checkout volume.",
            githubUrl = "https://github.com/developer/ecommerce-platform",
            liveDemoUrl = "https://storefront-demo.vercel.app",
            keyMetrics = listOf("$450k+" to "GMV Processed", "420ms" to "Page Load", "+28%" to "Checkout Conversion"),
            isFeatured = true,
            visualAccentHex = 0xFFF43F5E
        ),
        Project(
            id = "proj_06",
            number = "PROJECT 06",
            title = "Distributed Event Pipeline",
            subtitle = "Kafka Event-Driven Telemetry & Analytics Worker",
            category = "DevOps",
            description = "High-throughput real-time streaming pipeline processing asynchronous event logs with Golang workers and Kafka brokers.",
            overview = "Engineered to ingest telemetry, audit events, and user telemetry from microservices, batching into analytical data stores.",
            problem = "Direct synchronous HTTP database writes caused downstream API bottlenecks during high user activity periods.",
            solution = "Implemented an event-driven decoupled architecture using Apache Kafka message topics and concurrent Golang consumer groups.",
            features = listOf(
                "Apache Kafka multi-partition topic architecture with consumer groups",
                "Golang high-performance workers with graceful shutdown semantics",
                "Dead-letter queue handling for resilient fault tolerance",
                "Prometheus metrics scraping and Grafana dashboard visualization",
                "Docker Compose containerization for single-command orchestration"
            ),
            technologies = listOf("Golang", "Kafka", "Docker", "Redis", "PostgreSQL", "Prometheus"),
            challenges = "Guaranteeing exactly-once or idempotent message processing across multiple distributed consumer worker instances.",
            results = "Sustained throughput of 85,000 events/second per node with sub-15ms message latency.",
            githubUrl = "https://github.com/developer/kafka-golang-pipeline",
            liveDemoUrl = "https://telemetry-demo.pipeline.app",
            keyMetrics = listOf("85k/s" to "Msg Throughput", "<15ms" to "End-to-End Latency", "99.99%" to "Uptime"),
            isFeatured = false,
            visualAccentHex = 0xFFA78BFA
        )
    )

    // ==========================================
    // ARTICLES & BLOG POSTS (FROM USER PROMPT)
    // ==========================================
    val articles = listOf(
        Article(
            id = "art_01",
            title = "The simplest example is Kafka + Golang",
            subtitle = "Implementing a clean microservice architecture using Kafka, Golang and Docker",
            category = "Architecture",
            author = "Alex Vance",
            publishDate = "Oct 2026",
            readingTime = "6 min read",
            summary = "This article presents a simple way to implement a microservice architecture using Kafka, Golang and Docker with clean consumer groups.",
            introduction = "When building distributed systems, synchronous HTTP calls between microservices quickly become a bottleneck. Introducing Apache Kafka decouples your services and provides high fault tolerance. In this guide, we'll implement a clean, lightweight producer and consumer in Go without unnecessary boilerplate.",
            sections = listOf(
                ArticleSection(
                    heading = "1. Why Pair Golang with Apache Kafka?",
                    content = "Go's lightweight goroutines make it exceptionally well-suited for streaming architectures. A single Go worker can effortlessly manage thousands of Kafka partition consumers with negligible memory overhead compared to traditional runtimes."
                ),
                ArticleSection(
                    heading = "2. Producer Implementation in Go",
                    content = "Here is the minimal working producer using the standard kafka-go library. Notice how we configure message delivery guarantees with required ACKs:",
                    codeBlock = """package main

import (
    "context"
    "fmt"
    "log"
    "github.com/segmentio/kafka-go"
)

func produceMessage(topic string, key, value []byte) error {
    writer := &kafka.Writer{
        Addr:     kafka.TCP("localhost:9092"),
        Topic:    topic,
        Balancer: &kafka.LeastBytes{},
        RequiredAcks: kafka.RequireOne,
    }
    defer writer.Close()

    err := writer.WriteMessages(context.Background(),
        kafka.Message{
            Key:   key,
            Value: value,
        },
    )
    if err != nil {
        return fmt.Errorf("failed to write message: %w", err)
    }
    log.Println("Message published successfully!")
    return nil
}""",
                    codeLanguage = "go"
                ),
                ArticleSection(
                    heading = "3. Resilient Consumer with Graceful Shutdown",
                    content = "A production consumer must respect OS signals (SIGINT, SIGTERM) to commit current offsets before terminating. We use Go contexts to guarantee safe teardown without duplicate message processing.",
                    codeBlock = """func startConsumer(ctx context.Context, topic, groupID string) {
    reader := kafka.NewReader(kafka.ReaderConfig{
        Brokers:  []string{"localhost:9092"},
        GroupID:  groupID,
        Topic:    topic,
        MinBytes: 10e3, // 10KB
        MaxBytes: 10e6, // 10MB
    })
    defer reader.Close()

    for {
        msg, err := reader.ReadMessage(ctx)
        if err != nil {
            log.Printf("Consumer context terminated: %v", err)
            break
        }
        processEvent(msg.Key, msg.Value)
    }
}""",
                    codeLanguage = "go"
                )
            ),
            bestPractices = listOf(
                "Always set explicit Consumer Group IDs to enable automatic partition rebalancing",
                "Implement Dead-Letter Queues (DLQ) for malformed payload isolation",
                "Ensure idempotent consumer handlers by tracking unique message UUIDs",
                "Leverage Docker Compose for consistent local broker orchestration"
            ),
            conclusion = "By combining Go's concurrency primitives with Kafka's robust partitioning, you achieve horizontal scaling with minimal infrastructure overhead. Start small with a single topic before expanding your cluster."
        ),
        Article(
            id = "art_02",
            title = "Building REST APIs with Node.js & TypeScript",
            subtitle = "Structuring enterprise backends for maintainability and type safety",
            category = "Backend",
            author = "Alex Vance",
            publishDate = "Sep 2026",
            readingTime = "7 min read",
            summary = "A comprehensive deep dive into building modular, strongly typed RESTful APIs using Express, TypeScript, Zod validation, and layered architecture.",
            introduction = "A solid backend architecture separates business logic, route handlers, and database queries. Combining TypeScript with strict validation ensures errors are caught at compile time, long before reaching production.",
            sections = listOf(
                ArticleSection(
                    heading = "Layered Architecture in Express",
                    content = "Avoid putting database logic inside route handlers. Instead, adopt a strict three-tier architecture: Controllers handle HTTP serialization, Services orchestrate business rules, and Repositories interact with the database."
                ),
                ArticleSection(
                    heading = "Strict Request Validation with Zod",
                    content = "Never trust incoming client request bodies. Validate payloads before they touch your business logic:",
                    codeBlock = """import { z } from 'zod';
import { Request, Response, NextFunction } from 'express';

export const CreateUserSchema = z.object({
  email: z.string().email(),
  name: z.string().min(2).max(64),
  role: z.enum(['admin', 'member']).default('member'),
});

export const validateBody = (schema: z.ZodSchema) => 
  (req: Request, res: Response, next: NextFunction) => {
    const result = schema.safeParse(req.body);
    if (!result.success) {
      return res.status(400).json({ errors: result.error.format() });
    }
    req.body = result.data;
    next();
  };""",
                    codeLanguage = "typescript"
                )
            ),
            bestPractices = listOf(
                "Centralize error handling in an Express error middleware",
                "Use environment variables with strict runtime validation schemas",
                "Always write integration tests against ephemeral databases"
            ),
            conclusion = "Type safety transforms Node.js from a scripting environment into a rock-solid enterprise backend platform."
        ),
        Article(
            id = "art_03",
            title = "React Performance Optimization in 2026",
            subtitle = "Eliminating wasted renders, optimizing bundle size and memoization",
            category = "Frontend",
            author = "Alex Vance",
            publishDate = "Aug 2026",
            readingTime = "5 min read",
            summary = "Practical strategies for detecting unnecessary re-renders, leveraging compiler optimizations, and profiling modern React web apps.",
            introduction = "Performance problems in modern React rarely stem from the DOM itself—they almost always stem from redundant component tree evaluations and unmemoized object references.",
            sections = listOf(
                ArticleSection(
                    heading = "Understanding State Colocation",
                    content = "The simplest way to eliminate wasted renders is moving state as close as possible to the components that actually care about it. If only a modal needs an open/close toggle, lift it nowhere near the root provider."
                ),
                ArticleSection(
                    heading = "Derived State vs useEffect",
                    content = "Avoid using useEffect to calculate derived values. Calculate them during render or wrap them with useMemo when computation is expensive:",
                    codeBlock = """// ❌ Bad: Redundant render cycle
const [filtered, setFiltered] = useState([]);
useEffect(() => {
  setFiltered(items.filter(i => i.active));
}, [items]);

// ✅ Good: Pure derived calculation
const filtered = useMemo(() => {
  return items.filter(i => i.active);
}, [items]);""",
                    codeLanguage = "typescript"
                )
            ),
            bestPractices = listOf(
                "Keep component trees shallow and modular",
                "Profile with the React DevTools Flamegraph before premature optimization",
                "Utilize dynamic imports (React.lazy) for heavy route splits"
            ),
            conclusion = "Focus on state architecture first, bundle splitting second, and memoization hooks last."
        ),
        Article(
            id = "art_04",
            title = "PostgreSQL Database Design & Index Tuning",
            subtitle = "From normalization to composite B-tree and BRIN indexes under high load",
            category = "Database",
            author = "Alex Vance",
            publishDate = "Jul 2026",
            readingTime = "8 min read",
            summary = "Master database indexing strategies, analyze EXPLAIN ANALYZE queries, and prevent full table scans in PostgreSQL.",
            introduction = "When applications scale, the database is almost always the first bottleneck. Understanding how Postgres executes query plans makes the difference between a 10ms response and a 4-second timeout.",
            sections = listOf(
                ArticleSection(
                    heading = "Anatomy of an EXPLAIN ANALYZE Plan",
                    content = "Always inspect execution plans when querying large datasets. Look out for 'Seq Scan' on tables with millions of rows, which indicates a missing or unused index."
                ),
                ArticleSection(
                    heading = "Creating Targeted Composite Indexes",
                    content = "The order of columns in an index matters. Place high-cardinality equality filter columns first:",
                    codeBlock = """-- Inefficient: Queries filtering by status AND created_at will scan
CREATE INDEX idx_orders_created ON orders (created_at);

-- Optimal composite index matching query WHERE status = 'paid' AND created_at > ...
CREATE INDEX idx_orders_status_created 
ON orders (status, created_at DESC) 
INCLUDE (total_amount);""",
                    codeLanguage = "sql"
                )
            ),
            bestPractices = listOf(
                "Use partial indexes with WHERE clauses to reduce index size on disk",
                "Periodically run VACUUM ANALYZE to keep query planner statistics accurate",
                "Configure PgBouncer to manage connection pool spikes"
            ),
            conclusion = "Thoughtful schema constraints and selective indexing ensure your database remains lightning fast as data grows."
        ),
        Article(
            id = "art_05",
            title = "Docker for Developers: From Local to CI/CD",
            subtitle = "Multi-stage builds, rootless containers, and lightning caching",
            category = "DevOps",
            author = "Alex Vance",
            publishDate = "Jun 2026",
            readingTime = "6 min read",
            summary = "How to write production-grade Dockerfiles that minimize image size, harden container security, and speed up CI build pipelines.",
            introduction = "A messy Dockerfile wastes bandwidth, introduces security vulnerabilities, and slows down deployment cycles. Multi-stage builds are the golden standard for clean containerization.",
            sections = listOf(
                ArticleSection(
                    heading = "Multi-Stage Dockerfile Pattern",
                    content = "Separate your build toolchain from the minimal production runtime container:",
                    codeBlock = """# Build Stage
FROM node:20-alpine AS builder
WORKDIR /app
COPY package*.json ./
RUN npm ci
COPY . .
RUN npm run build

# Production Runtime Stage
FROM node:20-alpine AS runner
WORKDIR /app
ENV NODE_ENV=production
COPY --from=builder /app/package*.json ./
COPY --from=builder /app/node_modules ./node_modules
COPY --from=builder /app/dist ./dist
USER node
EXPOSE 3000
CMD ["node", "dist/server.js"]""",
                    codeLanguage = "dockerfile"
                )
            ),
            bestPractices = listOf(
                "Never run container processes as root user",
                "Leverage .dockerignore to exclude git files and local node_modules",
                "Order Dockerfile commands from least frequently changed to most frequently changed"
            ),
            conclusion = "Small, secure images speed up deployment velocity and reduce cloud infrastructure costs."
        ),
        Article(
            id = "art_06",
            title = "Building Android Apps with Kotlin & Jetpack Compose",
            subtitle = "Modern declarative UI, unidirectional data flow, and Material 3 polish",
            category = "Mobile",
            author = "Alex Vance",
            publishDate = "May 2026",
            readingTime = "6 min read",
            summary = "Why Jetpack Compose is the ultimate tool for modern Android development, creating sleek reactive interfaces with Kotlin.",
            introduction = "Imperative XML layouts and ViewFindViewById are firmly in the past. Jetpack Compose brings reactive, declarative UI patterns directly to native Android with unparalleled velocity.",
            sections = listOf(
                ArticleSection(
                    heading = "State Management with StateFlow",
                    content = "In Compose, UI is a direct transformation of state. Always expose UI state as an immutable StateFlow from your ViewModel:"
                ),
                ArticleSection(
                    heading = "Declarative Composable Pattern",
                    content = "Notice how concise and readable modern Compose components are:",
                    codeBlock = """@Composable
fun MetricBadge(
    label: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Surface(
        shape = RoundedCornerShape(12.dp),
        color = MaterialTheme.colorScheme.surfaceVariant,
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
        modifier = modifier
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = value, style = MaterialTheme.typography.titleMedium)
            Text(text = label, style = MaterialTheme.typography.labelSmall)
        }
    }
}""",
                    codeLanguage = "kotlin"
                )
            ),
            bestPractices = listOf(
                "Follow Unidirectional Data Flow (State goes down, Events go up)",
                "Always specify contentDescription on interactive elements for TalkBack accessibility",
                "Use WindowInsets handling to achieve seamless edge-to-edge screens"
            ),
            conclusion = "Kotlin and Jetpack Compose make building elegant, high-performance native apps an absolute pleasure."
        )
    )

    // ==========================================
    // SOCIAL LINKS
    // ==========================================
    val socialLinks = listOf(
        SocialLink("GitHub", "@alexvance-dev", profile.githubUrl),
        SocialLink("LinkedIn", "/in/alexvance-dev", profile.linkedinUrl),
        SocialLink("Telegram", "@alexvance_dev", profile.telegramUrl),
        SocialLink("X / Twitter", "@alexvance_tech", profile.xUrl),
        SocialLink("Email", profile.email, "mailto:${profile.email}")
    )

    // ==========================================
    // REACTIVE APP STATE (BOOKMARKS, THEME, CONTACTS)
    // ==========================================
    private val _themeMode = MutableStateFlow(ThemeMode.DARK)
    val themeMode: StateFlow<ThemeMode> = _themeMode.asStateFlow()

    private val _bookmarkedArticleIds = MutableStateFlow<Set<String>>(setOf("art_01"))
    val bookmarkedArticleIds: StateFlow<Set<String>> = _bookmarkedArticleIds.asStateFlow()

    private val _contactMessages = MutableStateFlow<List<ContactMessage>>(emptyList())
    val contactMessages: StateFlow<List<ContactMessage>> = _contactMessages.asStateFlow()

    fun setThemeMode(mode: ThemeMode) {
        _themeMode.value = mode
    }

    fun toggleArticleBookmark(articleId: String) {
        _bookmarkedArticleIds.update { current ->
            if (current.contains(articleId)) current - articleId else current + articleId
        }
    }

    fun isArticleBookmarked(articleId: String): Boolean {
        return _bookmarkedArticleIds.value.contains(articleId)
    }

    fun submitContactMessage(message: ContactMessage): Boolean {
        _contactMessages.update { listOf(message) + it }
        return true
    }

    fun getProjectById(id: String): Project? {
        return projects.find { it.id == id }
    }

    fun getArticleById(id: String): Article? {
        return articles.find { it.id == id }
    }
}
