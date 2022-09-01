import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CoreModule } from './core/core.module';
import { NotFoundComponent } from './core/not-found/not-found.component';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { HttpClientModule } from '@angular/common/http';
import { CanActivateViaLoggingEmpleado } from './canActivateViaLoggingEmpleado';
import { CanActivateViaLoggingAdministrador } from './canActivateViaLoggingAdministrador';

@NgModule({
  declarations: [
    AppComponent,
    NotFoundComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    CoreModule,
    HttpClientModule,
    FontAwesomeModule
  ],
  providers: [CanActivateViaLoggingEmpleado, CanActivateViaLoggingAdministrador],
  bootstrap: [AppComponent]
})
export class AppModule {}


