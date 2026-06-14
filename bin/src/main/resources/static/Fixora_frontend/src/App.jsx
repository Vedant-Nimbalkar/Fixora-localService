import React, { useState, useEffect } from 'react';
import { 
  Menu, X, MapPin, Star, ShieldCheck, Clock, MessageSquare, 
  Wrench, Zap, Sparkles, MonitorSmartphone, Paintbrush, Home, 
  Search, UserCheck, CalendarCheck, CheckCircle2, ChevronRight,
  User, Briefcase, LogIn, UserPlus, ArrowRight
} from 'lucide-react';

// Custom Social Media Icons (Since lucide-react removed brand icons)
const FacebookIcon = ({ className }) => (
  <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round" className={className}>
    <path d="M18 2h-3a5 5 0 0 0-5 5v3H7v4h3v8h4v-8h3l1-4h-4V7a1 1 0 0 1 1-1h3z"></path>
  </svg>
);

const TwitterIcon = ({ className }) => (
  <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round" className={className}>
    <path d="M22 4s-.7 2.1-2 3.4c1.6 10-9.4 17.3-18 11.6 2.2.1 4.4-.6 6-2C3 15.5.5 9.6 3 5c2.2 2.6 5.6 4.1 9 4-.9-4.2 4-6.6 7-3.8 1.1 0 3-1.2 3-1.2z"></path>
  </svg>
);

const InstagramIcon = ({ className }) => (
  <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round" className={className}>
    <rect x="2" y="2" width="20" height="20" rx="5" ry="5"></rect>
    <path d="M16 11.37A4 4 0 1 1 12.63 8 4 4 0 0 1 16 11.37z"></path>
    <line x1="17.5" y1="6.5" x2="17.51" y2="6.5"></line>
  </svg>
);

const LinkedinIcon = ({ className }) => (
  <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round" className={className}>
    <path d="M16 8a6 6 0 0 1 6 6v7h-4v-7a2 2 0 0 0-2-2 2 2 0 0 0-2 2v7h-4v-7a6 6 0 0 1 6-6z"></path>
    <rect x="2" y="9" width="4" height="12"></rect>
    <circle cx="4" cy="4" r="2"></circle>
  </svg>
);

export default function App() {
  const [isScrolled, setIsScrolled] = useState(false);
  const [mobileMenuOpen, setMobileMenuOpen] = useState(false);

  // Handle scroll effect for navbar
  useEffect(() => {
    const handleScroll = () => {
      setIsScrolled(window.scrollY > 20);
    };
    window.addEventListener('scroll', handleScroll);
    return () => window.removeEventListener('scroll', handleScroll);
  }, []);

  return (
    <div className="min-h-screen bg-[#F9FAFB] text-[#111827] font-sans selection:bg-blue-100 selection:text-blue-900 scroll-smooth">
      
      {/* Navbar */}
      <nav className={`fixed w-full z-50 transition-all duration-300 ${isScrolled ? 'bg-white/90 backdrop-blur-md shadow-sm py-3' : 'bg-transparent py-5'}`}>
        <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
          <div className="flex justify-between items-center">
            {/* Logo */}
            <div className="flex items-center gap-2 cursor-pointer">
              <div className="w-8 h-8 rounded-lg bg-gradient-to-br from-blue-600 to-violet-600 flex items-center justify-center shadow-md">
                <Wrench className="w-5 h-5 text-white" />
              </div>
              <span className="text-2xl font-bold tracking-tight bg-clip-text text-transparent bg-gradient-to-r from-blue-600 to-violet-600">
                Fixora
              </span>
            </div>

            {/* Desktop Menu */}
            <div className="hidden md:flex items-center space-x-8">
              {['Home', 'Services', 'How it Works', 'Features'].map((item) => (
                <a key={item} href={`#${item.toLowerCase().replace(/\s+/g, '-')}`} className="text-sm font-medium text-gray-600 hover:text-blue-600 transition-colors">
                  {item}
                </a>
              ))}
            </div>

            {/* Desktop CTA */}
            <div className="hidden md:flex">
              <a onClick={() => window.location.href='/user/register'} className="px-5 py-2.5 text-sm font-medium text-white bg-blue-600 rounded-lg hover:bg-blue-700 hover:scale-105 hover:shadow-lg hover:shadow-blue-600/20 transition-all duration-300">
                Login / Sign Up
              </a>
            </div>

            {/* Mobile Menu Button */}
            <div className="md:hidden flex items-center">
              <button onClick={() => setMobileMenuOpen(!mobileMenuOpen)} className="text-gray-600 hover:text-gray-900 focus:outline-none" aria-label="Toggle menu">
                {mobileMenuOpen ? <X className="w-6 h-6" /> : <Menu className="w-6 h-6" />}
              </button>
            </div>
          </div>
        </div>

        {/* Mobile Menu Panel */}
        {mobileMenuOpen && (
          <div className="md:hidden absolute top-full left-0 w-full bg-white shadow-xl border-t border-gray-100 py-4 px-4 flex flex-col gap-4">
            {['Home', 'Services', 'How it Works', 'Features'].map((item) => (
              <a key={item} href={`#${item.toLowerCase().replace(/\s+/g, '-')}`} className="text-base font-medium text-gray-700 p-2 rounded-md hover:bg-gray-50" onClick={() => setMobileMenuOpen(false)}>
                {item}
              </a>
            ))}
            <a onClick={() => window.location.href='/user/register'} className="w-full mt-2 px-5 py-3 text-sm font-medium text-white bg-blue-600 rounded-lg shadow-md text-center">
              Login / Sign Up
            </a>
          </div>
        )}
      </nav>

      {/* Hero Section */}
      <section id="home" className="relative pt-32 pb-20 lg:pt-48 lg:pb-32 overflow-hidden">
        {/* Abstract Background Shapes */}
        <div className="absolute top-0 left-1/2 -translate-x-1/2 w-[1000px] h-[500px] bg-gradient-to-br from-blue-400/20 to-violet-400/20 blur-3xl rounded-full -z-10 opacity-50 pointer-events-none"></div>
        
        <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
          <div className="flex flex-col lg:flex-row items-center gap-12 lg:gap-8">
            
            {/* Hero Content */}
            <div className="w-full lg:w-1/2 text-center lg:text-left">
              <div className="inline-flex items-center gap-2 px-3 py-1 rounded-full bg-blue-50 border border-blue-100 text-blue-600 text-sm font-medium mb-6">
                <span className="flex h-2 w-2 rounded-full bg-blue-600"></span>
                Smarter Local Services
              </div>
              <h1 className="text-4xl md:text-5xl lg:text-6xl font-bold leading-tight mb-6 tracking-tight text-gray-900">
                Find <span className="text-transparent bg-clip-text bg-gradient-to-r from-blue-600 to-emerald-500">Trusted Local</span><br className="hidden md:block"/> Services Instantly
              </h1>
              <p className="text-lg md:text-xl text-gray-600 mb-8 max-w-2xl mx-auto lg:mx-0">
                Connect with highly-rated plumbers, electricians, cleaners, and technicians in your area. Book in seconds, track in real-time.
              </p>
              <div className="flex flex-col sm:flex-row items-center justify-center lg:justify-start gap-4">
                <button onClick={() => window.location.href='/services'} className="w-full sm:w-auto px-8 py-4 text-base font-semibold text-white bg-blue-600 rounded-lg shadow-lg shadow-blue-600/30 hover:bg-blue-700 hover:-translate-y-1 transition-all duration-300">
                  Book a Service
                </button>
                <button onClick={() => window.location.href='/partner/register'} className="w-full sm:w-auto px-8 py-4 text-base font-semibold text-gray-700 bg-white border border-gray-200 rounded-lg shadow-sm hover:border-gray-300 hover:bg-gray-50 hover:-translate-y-1 transition-all duration-300">
                  Become a Provider
                </button>
              </div>
              
              <div className="mt-10 flex items-center justify-center lg:justify-start gap-4 text-sm text-gray-500">
                <div className="flex -space-x-2">
                  {[1,2,3,4].map((i) => (
                    <div key={i} className={`w-8 h-8 rounded-full border-2 border-white flex items-center justify-center text-xs text-white font-bold
                      ${i===1 ? 'bg-blue-500' : i===2 ? 'bg-emerald-500' : i===3 ? 'bg-violet-500' : 'bg-orange-400'}`}>
                      {String.fromCharCode(64+i)}
                    </div>
                  ))}
                </div>
                <div>
                  <div className="flex items-center text-amber-400">
                    <Star className="w-4 h-4 fill-current" />
                    <Star className="w-4 h-4 fill-current" />
                    <Star className="w-4 h-4 fill-current" />
                    <Star className="w-4 h-4 fill-current" />
                    <Star className="w-4 h-4 fill-current" />
                  </div>
                  <p className="mt-0.5"><strong className="text-gray-900 font-semibold">4.8/5</strong> from 10k+ users</p>
                </div>
              </div>
            </div>

            {/* Hero Visual - Premium CSS Composition */}
            <div className="w-full lg:w-1/2 relative flex justify-center lg:justify-end perspective-1000">
              <div className="relative w-full max-w-md h-[500px]">
                {/* Decorative background glow */}
                <div className="absolute inset-0 bg-gradient-to-tr from-emerald-400/20 via-blue-500/20 to-violet-500/20 blur-2xl rounded-full"></div>
                
                {/* Main App Mockup Card */}
                <div className="absolute inset-x-4 top-10 bottom-0 bg-white rounded-3xl shadow-2xl border border-gray-100 overflow-hidden transform transition-transform hover:scale-[1.02] duration-500 flex flex-col">
                  {/* Mock Map Background */}
                  <div className="absolute inset-0 bg-[url('data:image/svg+xml;base64,PHN2ZyB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciIHdpZHRoPSI0MCIgaGVpZ2h0PSI0MCI+CjxwYXRoIGQ9Ik0wIDBoNDB2NDBIMHoiIGZpbGw9IiNmOWZhZmIiLz4KPHBhdGggZD0iTTAgMGg0MHYxbS00MCAzOWg0MHYxbS0zOS00MHY0MG0zOS00MHY0MCIgc3Ryb2tlPSIjZTFlNThmIiBzdHJva2Utd2lkdGg9IjEiIGZpbGw9Im5vbmUiLz4KPC9zdmc+')] opacity-60"></div>
                  
                  {/* App Header */}
                  <div className="relative bg-white/90 backdrop-blur-md p-6 border-b border-gray-100">
                    <div className="flex justify-between items-center mb-4">
                      <div className="w-8 h-8 rounded-full bg-gray-100 flex items-center justify-center">
                        <Menu className="w-4 h-4 text-gray-600" />
                      </div>
                      <div className="w-10 h-10 rounded-full bg-blue-100 border-2 border-white shadow-sm overflow-hidden">
                        <img src={`https://api.dicebear.com/7.x/avataaars/svg?seed=Felix&backgroundColor=b6e3f4`} alt="User" />
                      </div>
                    </div>
                    <h3 className="text-xl font-bold text-gray-900">Current Location</h3>
                    <p className="text-sm text-gray-500 flex items-center gap-1 mt-1">
                      <MapPin className="w-3 h-3 text-blue-600" /> 123 Tech Avenue, NY
                    </p>
                  </div>

                  {/* Mock Map Elements */}
                  <div className="relative flex-1 p-6">
                    {/* Floating Provider Card 1 */}
                    <div className="absolute top-1/4 left-8 animate-bounce" style={{ animationDuration: '3s' }}>
                      <div className="bg-white p-2 rounded-xl shadow-lg border border-gray-100 flex items-center gap-3 w-48">
                        <div className="w-10 h-10 rounded-full bg-emerald-100 flex items-center justify-center flex-shrink-0">
                          <Wrench className="w-5 h-5 text-emerald-600" />
                        </div>
                        <div>
                          <p className="text-xs font-bold text-gray-900">John Plumber</p>
                          <p className="text-[10px] text-gray-500">2 mins away</p>
                        </div>
                      </div>
                      <div className="w-4 h-4 bg-white transform rotate-45 absolute -bottom-2 left-6 border-b border-r border-gray-100 shadow-sm"></div>
                    </div>

                    {/* Floating Provider Card 2 */}
                    <div className="absolute top-1/2 right-6 animate-bounce" style={{ animationDuration: '4s', animationDelay: '1s' }}>
                      <div className="w-12 h-12 rounded-full bg-blue-600 shadow-lg flex items-center justify-center border-4 border-white cursor-pointer hover:scale-110 transition-transform">
                        <Zap className="w-5 h-5 text-white" />
                      </div>
                    </div>

                    {/* Active Service Status */}
                    <div className="absolute bottom-6 left-6 right-6 bg-gray-900 text-white p-4 rounded-2xl shadow-xl flex items-center justify-between">
                      <div className="flex items-center gap-3">
                        <div className="w-10 h-10 rounded-full bg-emerald-500/20 flex items-center justify-center">
                          <CheckCircle2 className="w-6 h-6 text-emerald-400" />
                        </div>
                        <div>
                          <p className="text-sm font-bold">Booking Confirmed</p>
                          <p className="text-xs text-gray-400">Electrician arriving in 15m</p>
                        </div>
                      </div>
                      <ChevronRight className="w-5 h-5 text-gray-400" />
                    </div>
                  </div>
                </div>
              </div>
            </div>

          </div>
        </div>
      </section>

      {/* Services Section */}
      <section id="services" className="py-20 bg-white">
        <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
          <div className="text-center max-w-2xl mx-auto mb-16">
            <h2 className="text-3xl md:text-4xl font-bold text-gray-900 mb-4">Professional Services at Your Doorstep</h2>
            <p className="text-lg text-gray-600">Choose from our wide range of trusted, verified local professionals ready to help you.</p>
          </div>

          <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-6 md:gap-8">
            {[
              { title: 'Plumbing', icon: Wrench, color: 'text-blue-600', bg: 'bg-blue-50', desc: 'Leaks, pipe repairs, installations' },
              { title: 'Electrical', icon: Zap, color: 'text-emerald-500', bg: 'bg-emerald-50', desc: 'Wiring, fixtures, safety checks' },
              { title: 'Cleaning', icon: Sparkles, color: 'text-violet-500', bg: 'bg-violet-50', desc: 'Deep cleaning, regular maintenance' },
              { title: 'Appliance Repair', icon: MonitorSmartphone, color: 'text-orange-500', bg: 'bg-orange-50', desc: 'AC, fridge, washing machines' },
              { title: 'Painting', icon: Paintbrush, color: 'text-pink-500', bg: 'bg-pink-50', desc: 'Interior, exterior, texturing' },
              { title: 'Home Maintenance', icon: Home, color: 'text-teal-500', bg: 'bg-teal-50', desc: 'General repairs, carpentry' }
            ].map((service) => (
              <div key={service.title} className="group bg-white rounded-2xl p-6 shadow-sm border border-gray-100 hover:shadow-xl hover:shadow-gray-200/50 hover:-translate-y-1 transition-all duration-300 cursor-pointer">
                <div className={`w-14 h-14 rounded-xl ${service.bg} flex items-center justify-center mb-6 group-hover:scale-110 transition-transform duration-300`}>
                  <service.icon className={`w-7 h-7 ${service.color}`} />
                </div>
                <h3 className="text-xl font-bold text-gray-900 mb-2">{service.title}</h3>
                <p className="text-gray-500 text-sm mb-4">{service.desc}</p>
                <div className="flex items-center text-sm font-semibold text-blue-600 opacity-0 group-hover:opacity-100 transform translate-x-[-10px] group-hover:translate-x-0 transition-all duration-300">
                  Explore <ChevronRight className="w-4 h-4 ml-1" />
                </div>
              </div>
            ))}
          </div>
        </div>
      </section>

      {/* How It Works Section */}
      <section id="how-it-works" className="py-24 bg-gray-50 relative">
        <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
          <div className="text-center max-w-2xl mx-auto mb-20">
            <h2 className="text-3xl md:text-4xl font-bold text-gray-900 mb-4">How Fixora Works</h2>
            <p className="text-lg text-gray-600">Get your tasks done in four simple steps.</p>
          </div>

          <div className="relative">
            {/* Connecting Line (Desktop) */}
            <div className="hidden md:block absolute top-1/2 left-[10%] right-[10%] h-0.5 bg-gradient-to-r from-blue-200 via-emerald-200 to-violet-200 -translate-y-1/2 z-0"></div>

            <div className="grid grid-cols-1 md:grid-cols-4 gap-12 md:gap-6 relative z-10">
              {[
                { step: '01', title: 'Select Service', icon: Search, desc: 'Pick what you need help with.' },
                { step: '02', title: 'Choose Provider', icon: UserCheck, desc: 'Review profiles & ratings.' },
                { step: '03', title: 'Book Instantly', icon: CalendarCheck, desc: 'Pick a time that works for you.' },
                { step: '04', title: 'Get Work Done', icon: CheckCircle2, desc: 'Pay securely after completion.' }
              ].map((item) => (
                <div key={item.step} className="flex flex-col items-center text-center group">
                  <div className="w-20 h-20 bg-white rounded-2xl shadow-md border border-gray-100 flex items-center justify-center relative mb-6 group-hover:scale-110 group-hover:shadow-lg transition-all duration-300">
                    <span className="absolute -top-3 -right-3 w-8 h-8 bg-gray-900 text-white rounded-full flex items-center justify-center text-xs font-bold shadow-sm">
                      {item.step}
                    </span>
                    <item.icon className="w-8 h-8 text-blue-600 group-hover:text-emerald-500 transition-colors" />
                  </div>
                  <h3 className="text-lg font-bold text-gray-900 mb-2">{item.title}</h3>
                  <p className="text-sm text-gray-500 px-4">{item.desc}</p>
                </div>
              ))}
            </div>
          </div>
        </div>
      </section>

      {/* Features Section */}
      <section id="features" className="py-24 bg-white">
        <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
          <div className="flex flex-col lg:flex-row items-center gap-16">
            
            <div className="w-full lg:w-1/2">
              <h2 className="text-3xl md:text-4xl font-bold text-gray-900 mb-6">Built for Reliability & Convenience</h2>
              <p className="text-lg text-gray-600 mb-10">We've designed Fixora from the ground up to ensure you have a seamless, stress-free experience every time you book a service.</p>
              
              <div className="space-y-8">
                {[
                  { title: 'Verified Professionals', icon: ShieldCheck, color: 'text-blue-600', bg: 'bg-blue-100', desc: 'Every provider undergoes strict background checks.' },
                  { title: 'Real-Time Tracking', icon: Clock, color: 'text-emerald-600', bg: 'bg-emerald-100', desc: 'Know exactly when your provider will arrive.' },
                  { title: 'Easy Communication', icon: MessageSquare, color: 'text-violet-600', bg: 'bg-violet-100', desc: 'In-app chat to discuss details securely.' }
                ].map((feature) => (
                  <div key={feature.title} className="flex items-start gap-4 p-4 rounded-xl hover:bg-gray-50 transition-colors">
                    <div className={`w-12 h-12 rounded-xl ${feature.bg} flex items-center justify-center flex-shrink-0 mt-1`}>
                      <feature.icon className={`w-6 h-6 ${feature.color}`} />
                    </div>
                    <div>
                      <h4 className="text-xl font-bold text-gray-900 mb-1">{feature.title}</h4>
                      <p className="text-gray-600 text-sm leading-relaxed">{feature.desc}</p>
                    </div>
                  </div>
                ))}
              </div>
            </div>

            <div className="w-full lg:w-1/2">
              <div className="relative rounded-3xl overflow-hidden shadow-2xl">
                <img 
                  src="https://images.unsplash.com/photo-1581578731548-c64695cc6952?ixlib=rb-4.0.3&auto=format&fit=crop&w=1000&q=80" 
                  alt="Professional cleaning service" 
                  className="w-full h-auto object-cover hover:scale-105 transition-transform duration-700"
                />
                <div className="absolute inset-0 bg-gradient-to-t from-gray-900/60 to-transparent"></div>
                <div className="absolute bottom-6 left-6 right-6">
                  <div className="bg-white/90 backdrop-blur-sm p-4 rounded-xl flex items-center justify-between">
                    <div>
                      <p className="text-sm font-bold text-gray-900">Sarah M.</p>
                      <p className="text-xs text-gray-500">Expert Cleaner</p>
                    </div>
                    <div className="flex items-center gap-1 bg-amber-100 text-amber-700 px-2 py-1 rounded text-xs font-bold">
                      <Star className="w-3 h-3 fill-current" /> 4.9
                    </div>
                  </div>
                </div>
              </div>
            </div>

          </div>
        </div>
      </section>

      {/* Trust & Stats Section */}
      <section className="py-20 bg-gradient-to-br from-gray-900 to-blue-900 text-white">
        <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
          <div className="grid grid-cols-1 md:grid-cols-3 gap-10 text-center divide-y md:divide-y-0 md:divide-x divide-gray-700">
            <div className="p-4">
              <div className="text-5xl font-bold mb-2 bg-clip-text text-transparent bg-gradient-to-r from-blue-400 to-emerald-400">10k+</div>
              <p className="text-gray-300 font-medium">Services Completed</p>
            </div>
            <div className="p-4 pt-10 md:pt-4">
              <div className="text-5xl font-bold mb-2 bg-clip-text text-transparent bg-gradient-to-r from-emerald-400 to-teal-400">5k+</div>
              <p className="text-gray-300 font-medium">Verified Professionals</p>
            </div>
            <div className="p-4 pt-10 md:pt-4">
              <div className="text-5xl font-bold mb-2 bg-clip-text text-transparent bg-gradient-to-r from-teal-400 to-blue-400">4.8</div>
              <p className="text-gray-300 font-medium">Average Star Rating</p>
            </div>
          </div>

          {/* Mini Testimonial */}
          <div className="mt-16 max-w-3xl mx-auto bg-white/10 backdrop-blur-md rounded-2xl p-8 border border-white/10 text-center relative">
            <div className="absolute -top-6 left-1/2 -translate-x-1/2 w-12 h-12 bg-blue-500 rounded-full flex items-center justify-center border-4 border-gray-900">
              <Star className="w-5 h-5 text-white fill-current" />
            </div>
            <p className="text-lg md:text-xl font-medium text-gray-100 italic mb-6 mt-4">
              "Fixora completely changed how I manage my home. I needed a plumber urgently, and within 10 minutes, I had a highly-rated professional on the way. The app is beautifully designed and so easy to use."
            </p>
            <div>
              <p className="font-bold text-white">Emily R.</p>
              <p className="text-sm text-gray-400">Homeowner</p>
            </div>
          </div>
        </div>
      </section>

      {/* Portals Section (Replaced old CTA Section) */}
      <section id="portals" className="py-24 bg-gray-50 border-t border-gray-100">
        <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
          
          {/* Section Header */}
          <div className="text-center max-w-2xl mx-auto mb-16">
            <h2 className="text-3xl md:text-4xl font-bold text-gray-900 mb-4">
              Welcome to Fixora
            </h2>
            <p className="text-lg text-gray-600">
              Choose your portal below to get started, whether you're looking for a service, offering your skills, or managing the platform.
            </p>
          </div>

          {/* Portal Cards Grid */}
          <div className="grid grid-cols-1 md:grid-cols-3 gap-8">
            
            {/* 1. Customer Portal Card */}
            <div className="bg-white rounded-3xl p-8 shadow-sm border border-gray-100 hover:shadow-xl hover:-translate-y-1 transition-all duration-300 relative overflow-hidden group">
              <div className="absolute top-0 right-0 w-32 h-32 bg-blue-50 rounded-bl-full -mr-16 -mt-16 transition-transform group-hover:scale-110"></div>
              
              <div className="w-14 h-14 bg-blue-100 rounded-2xl flex items-center justify-center mb-6 relative z-10 text-blue-600">
                <User className="w-7 h-7" />
              </div>
              
              <h3 className="text-2xl font-bold text-gray-900 mb-2 relative z-10">For Customers</h3>
              <p className="text-gray-500 mb-8 relative z-10 min-h-[48px]">
                Find trusted professionals and book services with ease.
              </p>
              
              <div className="space-y-3 relative z-10">
                <a onClick={() => window.location.href='/services'} className="flex items-center justify-between w-full p-3 rounded-xl bg-gray-50 hover:bg-blue-50 hover:text-blue-600 text-gray-700 font-medium transition-colors">
                  <span className="flex items-center gap-2"><Search className="w-4 h-4" onClick={() => window.location.href='/services'}/> Browse Services</span>
                  <ArrowRight className="w-4 h-4" />
                </a>
                <a onClick={() => window.location.href='/user/register'} className="flex items-center justify-between w-full p-3 rounded-xl bg-blue-600 hover:bg-blue-700 text-white font-medium transition-colors shadow-md shadow-blue-600/20">
                  <span className="flex items-center gap-2"><UserPlus className="w-4 h-4" /> Create Account</span>
                  <ArrowRight className="w-4 h-4" />
                </a>
                <a onClick={() => window.location.href='/user/login'} className="flex items-center justify-between w-full p-3 rounded-xl bg-white border border-gray-200 hover:border-blue-300 hover:text-blue-600 text-gray-700 font-medium transition-colors">
                  <span className="flex items-center gap-2"><LogIn className="w-4 h-4" /> Login</span>
                  <ArrowRight className="w-4 h-4" />
                </a>
              </div>
            </div>

            {/* 2. Partner Portal Card */}
            <div className="bg-white rounded-3xl p-8 shadow-sm border border-gray-100 hover:shadow-xl hover:-translate-y-1 transition-all duration-300 relative overflow-hidden group">
              <div className="absolute top-0 right-0 w-32 h-32 bg-emerald-50 rounded-bl-full -mr-16 -mt-16 transition-transform group-hover:scale-110"></div>
              
              <div className="w-14 h-14 bg-emerald-100 rounded-2xl flex items-center justify-center mb-6 relative z-10 text-emerald-600">
                <Briefcase className="w-7 h-7" />
              </div>
              
              <h3 className="text-2xl font-bold text-gray-900 mb-2 relative z-10">For Partners</h3>
              <p className="text-gray-500 mb-8 relative z-10 min-h-[48px]">
                Grow your business by connecting with local clients needing your skills.
              </p>
              
              <div className="space-y-3 relative z-10">
                <a onClick={() => window.location.href='/partner/register'} className="flex items-center justify-between w-full p-3 rounded-xl bg-emerald-500 hover:bg-emerald-600 text-white font-medium transition-colors shadow-md shadow-emerald-500/20">
                  <span className="flex items-center gap-2"><UserPlus className="w-4 h-4" /> Apply as Partner</span>
                  <ArrowRight className="w-4 h-4" />
                </a>
                <a onClick={() => window.location.href='/partner/login'} className="flex items-center justify-between w-full p-3 rounded-xl bg-white border border-gray-200 hover:border-emerald-300 hover:text-emerald-600 text-gray-700 font-medium transition-colors">
                  <span className="flex items-center gap-2"><LogIn className="w-4 h-4" /> Partner Login</span>
                  <ArrowRight className="w-4 h-4" />
                </a>
              </div>
            </div>

            {/* 3. Admin Portal Card */}
            <div className="bg-white rounded-3xl p-8 shadow-sm border border-gray-100 hover:shadow-xl hover:-translate-y-1 transition-all duration-300 relative overflow-hidden group">
              <div className="absolute top-0 right-0 w-32 h-32 bg-violet-50 rounded-bl-full -mr-16 -mt-16 transition-transform group-hover:scale-110"></div>
              
              <div className="w-14 h-14 bg-violet-100 rounded-2xl flex items-center justify-center mb-6 relative z-10 text-violet-600">
                <ShieldCheck className="w-7 h-7" />
              </div>
              
              <h3 className="text-2xl font-bold text-gray-900 mb-2 relative z-10">Administration</h3>
              <p className="text-gray-500 mb-8 relative z-10 min-h-[48px]">
                Secure system access for authorized platform managers only.
              </p>
              
              <div className="space-y-3 relative z-10">
                <a onClick={() => window.location.href='/admin/login'} className="flex items-center justify-between w-full p-3 rounded-xl bg-gray-900 hover:bg-black text-white font-medium transition-colors shadow-md">
                  <span className="flex items-center gap-2"><ShieldCheck className="w-4 h-4" /> Admin Login</span>
                  <ArrowRight className="w-4 h-4" />
                </a>
              </div>
            </div>

          </div>
        </div>
      </section>

      {/* Footer */}
      <footer className="bg-gray-50 pt-16 pb-8 border-t border-gray-200 text-sm">
        <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
          <div className="grid grid-cols-1 md:grid-cols-4 gap-12 mb-12">
            
            <div className="col-span-1 md:col-span-1">
              <div className="flex items-center gap-2 mb-6">
                <div className="w-8 h-8 rounded-lg bg-blue-600 flex items-center justify-center">
                  <Wrench className="w-4 h-4 text-white" />
                </div>
                <span className="text-xl font-bold text-gray-900">Fixora</span>
              </div>
              <p className="text-gray-500 leading-relaxed mb-6">
                The smartest way to connect with trusted local professionals for all your home and office needs.
              </p>
              <div className="flex gap-4">
                <a href="#home" className="text-gray-400 hover:text-blue-600 transition-colors" aria-label="Facebook"><FacebookIcon className="w-5 h-5" /></a>
                <a href="#home" className="text-gray-400 hover:text-blue-400 transition-colors" aria-label="Twitter"><TwitterIcon className="w-5 h-5" /></a>
                <a href="#home" className="text-gray-400 hover:text-pink-600 transition-colors" aria-label="Instagram"><InstagramIcon className="w-5 h-5" /></a>
                <a href="#home" className="text-gray-400 hover:text-blue-800 transition-colors" aria-label="LinkedIn"><LinkedinIcon className="w-5 h-5" /></a>
              </div>
            </div>

            <div>
              <h4 className="font-bold text-gray-900 mb-6 uppercase tracking-wider text-xs">Services</h4>
              <ul className="space-y-4">
                {['Plumbing', 'Electrical', 'Cleaning', 'Appliance Repair', 'Home Painting'].map(item => (
                  <li key={item}><a href="#services" className="text-gray-600 hover:text-blue-600 transition-colors">{item}</a></li>
                ))}
              </ul>
            </div>

            <div>
              <h4 className="font-bold text-gray-900 mb-6 uppercase tracking-wider text-xs">Company</h4>
              <ul className="space-y-4">
                {['About Us', 'Careers', 'Provider Partner Program', 'Blog', 'Contact'].map(item => (
                  <li key={item}><a href="#home" className="text-gray-600 hover:text-blue-600 transition-colors">{item}</a></li>
                ))}
              </ul>
            </div>

            <div>
              <h4 className="font-bold text-gray-900 mb-6 uppercase tracking-wider text-xs">Legal</h4>
              <ul className="space-y-4">
                {['Terms of Service', 'Privacy Policy', 'Cookie Policy', 'Provider Agreement'].map(item => (
                  <li key={item}><a href="#home" className="text-gray-600 hover:text-blue-600 transition-colors">{item}</a></li>
                ))}
              </ul>
            </div>
            
          </div>

          <div className="pt-8 border-t border-gray-200 flex flex-col md:flex-row items-center justify-between text-gray-500">
            <p>© {new Date().getFullYear()} Fixora Inc. All rights reserved.</p>
            <div className="flex items-center gap-2 mt-4 md:mt-0">
              <span>Made with</span>
              <span className="text-red-500">♥</span>
              <span>for local communities.</span>
            </div>
          </div>
        </div>
      </footer>

    </div>
  );
}