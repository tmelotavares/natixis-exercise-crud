import { Routes } from '@angular/router';
import { ClientListComponent } from './client-list/client-list.component';
import { ClientFormComponent } from './client-form/client-form.component';

export const routes: Routes = [
  { path: '', component: ClientListComponent },
  { path: 'create', component: ClientFormComponent },
  { path: 'edit/:id', component: ClientFormComponent },
];