import { CommonModule } from "@angular/common";
import { Component, OnInit } from "@angular/core";
import { Router, RouterModule } from "@angular/router";
import { Client } from "../models/client.model";
import { ClientService } from "../services/client.service";

@Component({
  selector: 'app-client-list',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './client-list.component.html',
})
export class ClientListComponent implements OnInit {
  clients: Client[] = [];

  constructor(private service: ClientService, private router: Router) {}

  ngOnInit() {
    this.load();
  }

  load() {
    this.service.getAll().subscribe(data => (this.clients = data));
  }

  edit(id?: number) {
    this.router.navigate(['/edit', id]);
  }

  remove(id?: number) {
    if (!id) return;
    this.service.delete(id).subscribe(() => this.load());
  }
}
